package com.consecionarioapp.service;

import com.consecionarioapp.dto.ClienteRequestDTO;

import java.util.List;

public interface ClienteService {
    String registrarCliente(ClienteRequestDTO dto);
    List<ClienteRequestDTO> listarClientes();
    String actualizarCliente(ClienteRequestDTO dto); // ✔️ Recibe DTO con ID
    String eliminarCliente(int idCliente);

    // 🔧 FALTA para el botón "Editar"
    ClienteRequestDTO obtenerClientePorId(int idCliente); // <- AGREGA ESTO
}

