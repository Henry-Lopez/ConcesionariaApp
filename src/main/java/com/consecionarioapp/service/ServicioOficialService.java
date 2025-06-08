package com.consecionarioapp.service;

import com.consecionarioapp.dto.ServicioOficialRequestDTO;
import java.util.List;

public interface ServicioOficialService {
    String registrarServicio(ServicioOficialRequestDTO dto);
    List<ServicioOficialRequestDTO> listarServicios();
    ServicioOficialRequestDTO obtenerPorId(Integer idServicio); // 👈 Añadido
    String actualizarServicio(ServicioOficialRequestDTO dto);
    String eliminarServicio(Integer idServicio);
}
