package com.consecionarioapp.service;

import com.consecionarioapp.dto.VentaRequestDTO;

import java.util.List;

public interface VentaService {
    // Crear
    String registrarVenta(VentaRequestDTO dto);

    // Leer todos
    List<VentaRequestDTO> listarVentas();

    // Leer uno por ID
    VentaRequestDTO obtenerVentaPorId(int idVenta);

    // Actualizar
    String actualizarVenta(VentaRequestDTO dto);

    // Eliminar
    String eliminarVenta(int idVenta);
}
