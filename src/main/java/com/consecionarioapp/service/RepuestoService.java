package com.consecionarioapp.service;

import com.consecionarioapp.dto.RepuestoRequestDTO;

import java.util.List;

public interface RepuestoService {
    String registrarRepuesto(RepuestoRequestDTO dto);
    List<RepuestoRequestDTO> listarRepuestos();
    String actualizarRepuesto(int idRepuesto, RepuestoRequestDTO dto);
    String eliminarRepuesto(int idRepuesto);

    // 🔧 FALTA
    RepuestoRequestDTO obtenerRepuestoPorId(int idRepuesto); // <- AGREGA ESTO
}

