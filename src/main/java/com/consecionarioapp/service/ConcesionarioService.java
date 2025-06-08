package com.consecionarioapp.service;

import com.consecionarioapp.dto.ConcesionarioRequestDTO;
import java.util.List;

public interface ConcesionarioService {
    String registrarConcesionario(ConcesionarioRequestDTO dto);
    List<ConcesionarioRequestDTO> listarConcesionarios();
    ConcesionarioRequestDTO obtenerConcesionarioPorId(Integer idConcesionario);
    String actualizarConcesionario(ConcesionarioRequestDTO dto);
    String eliminarConcesionario(Integer idConcesionario);
}
