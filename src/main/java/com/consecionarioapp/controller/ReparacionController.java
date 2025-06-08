package com.consecionarioapp.controller;

import com.consecionarioapp.dto.ReparacionRequestDTO;
import com.consecionarioapp.service.ReparacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reparaciones")
public class ReparacionController {

    private final ReparacionService reparacionService;

    public ReparacionController(ReparacionService reparacionService) {
        this.reparacionService = reparacionService;
    }

    // ✅ Crear reparación
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarReparacion(@RequestBody ReparacionRequestDTO dto) {
        String resultado = reparacionService.registrarReparacion(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todas las reparaciones
    @GetMapping("/listar")
    public ResponseEntity<List<ReparacionRequestDTO>> listarReparaciones() {
        return ResponseEntity.ok(reparacionService.listarReparaciones());
    }

    // ✅ Obtener reparación por ID (nuevo)
    @GetMapping("/{id}")
    public ResponseEntity<ReparacionRequestDTO> obtenerReparacionPorId(@PathVariable int id) {
        ReparacionRequestDTO dto = reparacionService.obtenerReparacionPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar reparación por ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarReparacion(@PathVariable int id, @RequestBody ReparacionRequestDTO dto) {
        String resultado = reparacionService.actualizarReparacion(id, dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar reparación por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarReparacion(@PathVariable int id) {
        String resultado = reparacionService.eliminarReparacion(id);
        return ResponseEntity.ok(resultado);
    }
}
