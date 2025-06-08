package com.consecionarioapp.controller;

import com.consecionarioapp.dto.*;
import com.consecionarioapp.service.ReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/ventas-por-mes")
    public ResponseEntity<List<ReporteVentasPorMesDTO>> getVentasPorMes(
            @RequestParam int mes,
            @RequestParam int anio
    ) {
        return ResponseEntity.ok(reporteService.obtenerVentasPorMes(mes, anio));
    }

    @GetMapping("/ventas-por-empleado")
    public ResponseEntity<List<ReporteVentasPorEmpleadoDTO>> getVentasPorEmpleado() {
        return ResponseEntity.ok(reporteService.obtenerVentasPorEmpleado());
    }

    @GetMapping("/modelos-mas-vendidos")
    public ResponseEntity<List<ReporteModelosMasVendidosDTO>> getModelosMasVendidos() {
        return ResponseEntity.ok(reporteService.obtenerModelosMasVendidos());
    }

    @GetMapping("/ingresos-totales")
    public ResponseEntity<List<ReporteIngresosTotalesDTO>> getIngresosTotales() {
        return ResponseEntity.ok(reporteService.obtenerIngresosTotales());
    }

    @GetMapping("/reparaciones-por-mecanico")
    public ResponseEntity<List<ReporteReparacionesPorMecanicoDTO>> getReparacionesPorMecanico() {
        return ResponseEntity.ok(reporteService.obtenerReparacionesPorMecanico());
    }

    @GetMapping("/repuestos-mas-usados")
    public ResponseEntity<List<ReporteRepuestosMasUsadosDTO>> getRepuestosMasUsados() {
        return ResponseEntity.ok(reporteService.obtenerRepuestosMasUsados());
    }

    @GetMapping("/historial-cliente/{idCliente}")
    public ResponseEntity<List<HistorialClienteDTO>> getHistorialCliente(@PathVariable int idCliente) {
        return ResponseEntity.ok(reporteService.obtenerHistorialCliente(idCliente));
    }
}
