package com.poo.Terceira.Services;

import com.poo.Terceira.Models.Categoria;
import com.poo.Terceira.Repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService{
    private CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> findAll(){
        return this.categoriaRepository.findAll();
    }

    public Categoria createCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria findCategoriaById(Long id){
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Não encontrado"));
    }

    public Categoria updateCategoria(Long id, Categoria categoriaAtualizado){
        Categoria categoria = findCategoriaById(id);
        categoria.setNome(categoriaAtualizado.getNome());
        categoriaRepository.save(categoria);
        return categoria;
    }

    public void deleteCategoria(Long id){
        Categoria categoria = findCategoriaById(id);
        categoriaRepository.delete(categoria);
    }

}
