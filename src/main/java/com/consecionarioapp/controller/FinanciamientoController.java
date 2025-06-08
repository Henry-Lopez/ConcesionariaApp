package com.consecionarioapp.controller;

import com.consecionarioapp.dto.FinanciamientoRequestDTO;
import com.consecionarioapp.service.FinanciamientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financiamientos")
@CrossOrigin("*")
public class FinanciamientoController {

    private final FinanciamientoService financiamientoService;

    public FinanciamientoController(FinanciamientoService financiamientoService) {
        this.financiamientoService = financiamientoService;
    }

    // ✅ Crear financiamiento
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody FinanciamientoRequestDTO dto) {
        String resultado = financiamientoService.registrarFinanciamiento(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos
    @GetMapping("/listar")
    public ResponseEntity<List<FinanciamientoRequestDTO>> listar() {
        return ResponseEntity.ok(financiamientoService.listarFinanciamientos());
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<FinanciamientoRequestDTO> obtenerPorId(@PathVariable int id) {
        return ResponseEntity.ok(financiamientoService.obtenerFinanciamientoPorId(id));
    }

    // ✅ Obtener por ID de venta
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<FinanciamientoRequestDTO> obtenerPorVenta(@PathVariable int idVenta) {
        return ResponseEntity.ok(financiamientoService.obtenerPorVenta(idVenta));
    }

    // ✅ Actualizar
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody FinanciamientoRequestDTO dto) {
        return ResponseEntity.ok(financiamientoService.actualizarFinanciamiento(dto));
    }

    // ✅ Eliminar
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        return ResponseEntity.ok(financiamientoService.eliminarFinanciamiento(id));
    }

    // ✅ NUEVO: listar solo los financiamientos que aún no tienen banco
    @GetMapping("/disponibles")
    public ResponseEntity<List<FinanciamientoRequestDTO>> listarFinanciamientosSinBanco() {
        return ResponseEntity.ok(financiamientoService.listarSinBanco());
    }
}
