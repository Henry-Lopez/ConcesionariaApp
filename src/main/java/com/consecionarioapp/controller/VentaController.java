package com.consecionarioapp.controller;

import com.consecionarioapp.dto.VentaRequestDTO;
import com.consecionarioapp.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    // ✅ Crear venta
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVenta(@RequestBody VentaRequestDTO dto) {
        String resultado = ventaService.registrarVenta(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todas las ventas
    @GetMapping("/listar")
    public ResponseEntity<List<VentaRequestDTO>> listarVentas() {
        return ResponseEntity.ok(ventaService.listarVentas());
    }

    // ✅ Obtener una venta por ID (nuevo)
    @GetMapping("/{id}")
    public ResponseEntity<VentaRequestDTO> obtenerVentaPorId(@PathVariable int id) {
        VentaRequestDTO dto = ventaService.obtenerVentaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarVenta(@PathVariable int id, @RequestBody VentaRequestDTO dto) {
        dto.setIdVenta(id);
        String resultado = ventaService.actualizarVenta(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar venta por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable int id) {
        String resultado = ventaService.eliminarVenta(id);
        return ResponseEntity.ok(resultado);
    }
}
