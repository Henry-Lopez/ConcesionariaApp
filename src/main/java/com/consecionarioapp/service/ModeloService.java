package com.consecionarioapp.service;

import com.consecionarioapp.dto.ModeloRequestDTO;

import java.util.List;

public interface ModeloService {
    String registrarModelo(ModeloRequestDTO dto);
    List<ModeloRequestDTO> listarModelos();
    String actualizarModelo(int idModelo, ModeloRequestDTO dto);
    String eliminarModelo(int idModelo);
    ModeloRequestDTO obtenerModeloPorId(int idModelo); // extra
}
