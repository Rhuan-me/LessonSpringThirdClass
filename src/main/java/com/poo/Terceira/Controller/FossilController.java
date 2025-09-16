package com.poo.Terceira.Controller;

import com.poo.Terceira.Models.Fossil;
import com.poo.Terceira.Services.FossilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fossil")
public class FossilController {
   private FossilService fossilService;

   private ResponseEntity<Map<String, Object>> buildErrorResponse(String error, String message, HttpStatus status){
       Map<String, Object> body = new HashMap<>();
       body.put("timestamp", LocalDateTime.now());
       body.put("status", status);
       body.put("error", error);
       body.put("message", message);
       return ResponseEntity.status(status).body(body);
   }

   public FossilController(FossilService fossilService) {
      this.fossilService = fossilService;
   }

   @GetMapping
   public ResponseEntity<List<Fossil>> findAll(){
       return  ResponseEntity.ok(fossilService.findAll());
   }

   @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable Long id){
       try {
           return ResponseEntity.ok(fossilService.findFossilById(id));
       } catch (RuntimeException e){
           return buildErrorResponse("Fossil não encontrada", e.getMessage(), HttpStatus.NOT_FOUND);
       }
   }

   @PostMapping
    public ResponseEntity<Fossil> add(@RequestBody Fossil fossil){
       return ResponseEntity.ok(fossilService.createFossil(fossil));
   }

   @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Fossil fossil) {
       try {
           return ResponseEntity.ok(fossilService.updateFossil(id, fossil));
       } catch (RuntimeException e) {
           return buildErrorResponse("Fossil não encontrada", e.getMessage(), HttpStatus.NOT_FOUND);
       }
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
       try {
           fossilService.deleteFossil(id);
           return ResponseEntity.noContent().build();
       } catch (RuntimeException e) {
           return buildErrorResponse("Fossil não encontrada", e.getMessage(), HttpStatus.NOT_FOUND);
       }
   }
}
