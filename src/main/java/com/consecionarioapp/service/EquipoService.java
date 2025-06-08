package com.consecionarioapp.service;

import com.consecionarioapp.dto.EquipoRequestDTO;

import java.util.List;

public interface EquipoService {
    String registrarEquipo(EquipoRequestDTO dto);
    List<EquipoRequestDTO> listarEquipos();
    EquipoRequestDTO obtenerEquipoPorId(Integer idEquipo);
    String actualizarEquipo(EquipoRequestDTO dto);
    String eliminarEquipo(Integer idEquipo);
}
