package com.consecionarioapp.service;

import com.consecionarioapp.dto.ReparacionRequestDTO;

import java.util.List;

public interface ReparacionService {
    String registrarReparacion(ReparacionRequestDTO dto);
    List<ReparacionRequestDTO> listarReparaciones();
    String actualizarReparacion(int idReparacion, ReparacionRequestDTO dto);
    String eliminarReparacion(int idReparacion);

    // 🔧 FALTA
    ReparacionRequestDTO obtenerReparacionPorId(int idReparacion); // <- AGREGA ESTO
}

