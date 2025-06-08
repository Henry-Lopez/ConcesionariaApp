package com.consecionarioapp.service;

import com.consecionarioapp.dto.BancoRequestDTO;

import java.util.List;

public interface BancoService {

    String registrarBanco(BancoRequestDTO dto);

    List<BancoRequestDTO> listarBancos();

    BancoRequestDTO obtenerBancoPorId(int idBanco);

    String actualizarBanco(BancoRequestDTO dto);

    String eliminarBanco(int idBanco);
}
