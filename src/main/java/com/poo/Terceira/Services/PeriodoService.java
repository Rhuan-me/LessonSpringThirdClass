package com.poo.Terceira.Services;

import com.poo.Terceira.Models.Periodo;
import com.poo.Terceira.Repositories.PeriodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodoService {
    private PeriodoRepository periodoRepository;

    public PeriodoService(PeriodoRepository periodoRepository) {
        this.periodoRepository = periodoRepository;
    }

    public List<Periodo> findAll(){
        return this.periodoRepository.findAll();
    }

    public Periodo createPeriodo(Periodo periodo){
        return periodoRepository.save(periodo);
    }

    public Periodo findPeriodoById(Long id){
        return periodoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public Periodo updatePeriodo(Long id, Periodo periodoAtualizado){
        Periodo periodo = findPeriodoById(id);
        periodo.setNome(periodoAtualizado.getNome());
        periodoRepository.save(periodo);
        return periodo;
    }

    public void deletePeriodo(Long id){
        Periodo periodo = findPeriodoById(id);
        periodoRepository.delete(periodo);
    }
}
