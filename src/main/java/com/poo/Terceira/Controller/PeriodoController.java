package com.poo.Terceira.Controller;

import com.poo.Terceira.Models.Periodo;
import com.poo.Terceira.Services.PeriodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/periodo")
public class PeriodoController {
    private PeriodoService periodoService;

    private ResponseEntity<Map<String, Object>> buildErrorResponse(String error, String message, HttpStatus status){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("error", error);
        body.put("message", message);

        return ResponseEntity.status(status).body(body);
    }

    public PeriodoController(PeriodoService periodoService) {
        this.periodoService = periodoService;
    }

    @GetMapping
    public ResponseEntity<List<Periodo>> findAll(){
        return ResponseEntity.ok(periodoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(periodoService.findPeriodoById(id));
        } catch (RuntimeException e){
            return buildErrorResponse("Período não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Periodo> add(@RequestBody Periodo periodo){
        return ResponseEntity.ok(periodoService.createPeriodo(periodo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Periodo periodo){
        try{
            return ResponseEntity.ok(periodoService.updatePeriodo(id, periodo));
        } catch (RuntimeException e){
            return buildErrorResponse("Período não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            periodoService.deletePeriodo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return buildErrorResponse("Período não encontrado", e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
