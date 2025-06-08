package com.consecionarioapp.repository;

import com.consecionarioapp.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
}
