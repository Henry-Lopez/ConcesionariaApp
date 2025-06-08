package com.consecionarioapp.controller;

import com.consecionarioapp.dto.ConcesionarioRequestDTO;
import com.consecionarioapp.service.ConcesionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concesionarios")
@CrossOrigin("*")
public class ConcesionarioController {

    private final ConcesionarioService concesionarioService;

    public ConcesionarioController(ConcesionarioService concesionarioService) {
        this.concesionarioService = concesionarioService;
    }

    // ✅ Registrar concesionario
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody ConcesionarioRequestDTO dto) {
        String resultado = concesionarioService.registrarConcesionario(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar concesionarios
    @GetMapping("/listar")
    public ResponseEntity<List<ConcesionarioRequestDTO>> listar() {
        return ResponseEntity.ok(concesionarioService.listarConcesionarios());
    }

    // ✅ Obtener concesionario por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConcesionarioRequestDTO> obtenerPorId(@PathVariable int id) {
        ConcesionarioRequestDTO dto = concesionarioService.obtenerConcesionarioPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar concesionario
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody ConcesionarioRequestDTO dto) {
        String resultado = concesionarioService.actualizarConcesionario(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar concesionario
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        String resultado = concesionarioService.eliminarConcesionario(id);
        return ResponseEntity.ok(resultado);
    }
}
