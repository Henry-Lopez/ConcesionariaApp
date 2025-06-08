package com.consecionarioapp.service;

import com.consecionarioapp.dto.PedidoImportacionDTO;
import com.consecionarioapp.dto.PedidoResumenDTO;
import com.consecionarioapp.dto.VistaPedidoImportacionDTO;

import java.util.List;

public interface PedidoImportacionService {

    String registrarPedido(PedidoImportacionDTO dto);

    List<PedidoImportacionDTO> listarPedidos();

    String actualizarPedido(PedidoImportacionDTO dto);

    String eliminarPedido(Integer idPedido);

    PedidoImportacionDTO obtenerPedidoPorId(Integer idPedido);

    // ✅ Nuevo método para listar desde la vista
    List<VistaPedidoImportacionDTO> listarVistaPedidos();

    List<PedidoResumenDTO> obtenerResumenPedidos();
}
