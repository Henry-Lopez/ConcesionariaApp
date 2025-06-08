package com.consecionarioapp.controller;

import com.consecionarioapp.dto.FacturaVentaDTO;
import com.consecionarioapp.service.FacturaVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/facturas/ventas")
public class FacturaVentaController {

    private final FacturaVentaService facturaVentaService;

    public FacturaVentaController(FacturaVentaService facturaVentaService) {
        this.facturaVentaService = facturaVentaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FacturaVentaDTO>> listarFacturas() {
        return ResponseEntity.ok(facturaVentaService.listarFacturasVenta());
    }
}
