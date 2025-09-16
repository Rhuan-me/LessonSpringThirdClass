package com.poo.Terceira.Repositories;

import com.poo.Terceira.Models.Fossil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FossilRepository extends JpaRepository<Fossil,Long>{
}
