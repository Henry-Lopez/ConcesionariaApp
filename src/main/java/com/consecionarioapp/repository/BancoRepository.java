package com.consecionarioapp.repository;

import com.consecionarioapp.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Integer> {
}
