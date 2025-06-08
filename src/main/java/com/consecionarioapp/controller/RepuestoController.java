package com.consecionarioapp.controller;

import com.consecionarioapp.dto.RepuestoRequestDTO;
import com.consecionarioapp.service.RepuestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repuestos")
@CrossOrigin("*")
public class RepuestoController {

    private final RepuestoService repuestoService;

    public RepuestoController(RepuestoService repuestoService) {
        this.repuestoService = repuestoService;
    }

    // ✅ Crear repuesto
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarRepuesto(@RequestBody RepuestoRequestDTO dto) {
        String resultado = repuestoService.registrarRepuesto(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los repuestos
    @GetMapping("/listar")
    public ResponseEntity<List<RepuestoRequestDTO>> listarRepuestos() {
        return ResponseEntity.ok(repuestoService.listarRepuestos());
    }

    // ✅ Obtener repuesto por ID (nuevo)
    @GetMapping("/{id}")
    public ResponseEntity<RepuestoRequestDTO> obtenerRepuestoPorId(@PathVariable int id) {
        RepuestoRequestDTO dto = repuestoService.obtenerRepuestoPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar repuesto por ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarRepuesto(@PathVariable int id, @RequestBody RepuestoRequestDTO dto) {
        String resultado = repuestoService.actualizarRepuesto(id, dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar repuesto por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRepuesto(@PathVariable int id) {
        String resultado = repuestoService.eliminarRepuesto(id);
        return ResponseEntity.ok(resultado);
    }
}
