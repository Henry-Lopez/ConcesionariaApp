package com.consecionarioapp.service;

import com.consecionarioapp.dto.MarcaRequestDTO;

import java.util.List;

public interface MarcaService {
    String registrarMarca(MarcaRequestDTO dto);
    List<MarcaRequestDTO> listarMarcas();
    MarcaRequestDTO obtenerMarcaPorId(Integer idMarca);
    String actualizarMarca(MarcaRequestDTO dto);
    String eliminarMarca(Integer idMarca);
}
