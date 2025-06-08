package com.consecionarioapp.service;

import com.consecionarioapp.dto.MecanicoRequestDTO;

import java.util.List;

public interface MecanicoService {
    String registrarMecanico(MecanicoRequestDTO dto);
    List<MecanicoRequestDTO> listarMecanicos();
    String actualizarMecanico(MecanicoRequestDTO dto);
    String eliminarMecanico(Integer idMecanico);
    MecanicoRequestDTO obtenerMecanicoPorId(Integer idMecanico);
}
