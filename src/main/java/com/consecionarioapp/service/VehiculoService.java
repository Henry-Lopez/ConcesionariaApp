package com.consecionarioapp.service;

import com.consecionarioapp.dto.VehiculoRequestDTO;

import java.util.List;

public interface VehiculoService {
    String registrarVehiculo(VehiculoRequestDTO dto);
    List<VehiculoRequestDTO> listarVehiculos();
    String actualizarVehiculo(String nroChasis, VehiculoRequestDTO dto);
    String eliminarVehiculo(String nroChasis);
    VehiculoRequestDTO obtenerVehiculoPorNroChasis(String nroChasis);

    // ✅ NUEVO MÉTODO PARA VEHÍCULOS EN STOCK
    List<VehiculoRequestDTO> listarVehiculosDisponibles();
}
