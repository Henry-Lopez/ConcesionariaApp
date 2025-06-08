package com.consecionarioapp.service;

import com.consecionarioapp.dto.*;

import java.util.List;

public interface ReporteService {
    List<ReporteVentasPorMesDTO> obtenerVentasPorMes(int mes, int anio);
    List<ReporteVentasPorEmpleadoDTO> obtenerVentasPorEmpleado();
    List<ReporteIngresosTotalesDTO> obtenerIngresosTotales();
    List<ReporteModelosMasVendidosDTO> obtenerModelosMasVendidos();
    List<ReporteReparacionesPorMecanicoDTO> obtenerReparacionesPorMecanico();
    List<ReporteRepuestosMasUsadosDTO> obtenerRepuestosMasUsados();
    List<HistorialClienteDTO> obtenerHistorialCliente(int idCliente);

}
