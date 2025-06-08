package com.consecionarioapp.repository;

import com.consecionarioapp.model.Financiamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanciamientoRepository extends JpaRepository<Financiamiento, Integer> {
}
