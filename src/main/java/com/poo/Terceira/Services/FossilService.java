package com.poo.Terceira.Services;

import com.poo.Terceira.Models.Fossil;
import com.poo.Terceira.Repositories.FossilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FossilService {
    private FossilRepository fossilRepository;

    public FossilService(FossilRepository fossilRepository) {
        this.fossilRepository = fossilRepository;
    }

    public List<Fossil> findAll(){
        return this.fossilRepository.findAll();
    }

    public Fossil createFossil(Fossil fossil){
        return fossilRepository.save(fossil);
    }

    public Fossil findFossilById(Long id){
        return fossilRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public Fossil updateFossil(Long id, Fossil fossilAtualizado){
        Fossil fossil = findFossilById(id);
        fossil.setNome(fossilAtualizado.getNome());
        fossilRepository.save(fossil);
        return fossil;
    }

    public void deleteFossil(Long id){
        Fossil fossil = findFossilById(id);
        fossilRepository.delete(fossil);
    }
}
