package com.consecionarioapp.controller;

import com.consecionarioapp.dto.FacturaReparacionDTO;
import com.consecionarioapp.service.FacturaReparacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/facturas/reparaciones")
public class FacturaReparacionController {

    private final FacturaReparacionService facturaReparacionService;

    public FacturaReparacionController(FacturaReparacionService facturaReparacionService) {
        this.facturaReparacionService = facturaReparacionService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FacturaReparacionDTO>> listarFacturas() {
        return ResponseEntity.ok(facturaReparacionService.listarFacturasReparacion());
    }
}
