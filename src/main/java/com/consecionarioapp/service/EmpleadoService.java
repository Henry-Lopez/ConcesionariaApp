package com.consecionarioapp.service;

import com.consecionarioapp.dto.EmpleadoRequestDTO;

import java.util.List;

public interface EmpleadoService {
    String registrarEmpleado(EmpleadoRequestDTO dto);
    List<EmpleadoRequestDTO> listarEmpleados();
    String actualizarEmpleado(EmpleadoRequestDTO dto);
    String eliminarEmpleado(int idEmpleado);

    // 🔧 FALTA para botón "Editar"
    EmpleadoRequestDTO obtenerEmpleadoPorId(int idEmpleado); // <- AGREGA ESTO
}

