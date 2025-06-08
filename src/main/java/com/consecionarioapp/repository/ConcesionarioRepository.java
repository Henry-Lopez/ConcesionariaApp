package com.consecionarioapp.repository;

import com.consecionarioapp.model.Concesionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcesionarioRepository extends JpaRepository<Concesionario, Integer> {
}
