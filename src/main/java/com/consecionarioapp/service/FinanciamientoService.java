package com.consecionarioapp.service;

import com.consecionarioapp.dto.FinanciamientoRequestDTO;

import java.util.List;

public interface FinanciamientoService {

    String registrarFinanciamiento(FinanciamientoRequestDTO dto);

    List<FinanciamientoRequestDTO> listarFinanciamientos();

    FinanciamientoRequestDTO obtenerFinanciamientoPorId(int idFinanciamiento);

    String actualizarFinanciamiento(FinanciamientoRequestDTO dto);

    String eliminarFinanciamiento(int idFinanciamiento);

    FinanciamientoRequestDTO obtenerPorVenta(int idVenta);

    // ✅ Nuevo método
    List<FinanciamientoRequestDTO> listarSinBanco();
}
