package com.consecionarioapp.controller;

import com.consecionarioapp.dto.EquipoRequestDTO;
import com.consecionarioapp.service.EquipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin("*")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // ✅ Crear equipo
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarEquipo(@RequestBody EquipoRequestDTO dto) {
        String resultado = equipoService.registrarEquipo(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los equipos
    @GetMapping("/listar")
    public ResponseEntity<List<EquipoRequestDTO>> listarEquipos() {
        return ResponseEntity.ok(equipoService.listarEquipos());
    }

    // ✅ Obtener equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<EquipoRequestDTO> obtenerEquipoPorId(@PathVariable int id) {
        EquipoRequestDTO dto = equipoService.obtenerEquipoPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar equipo
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarEquipo(@RequestBody EquipoRequestDTO dto) {
        String resultado = equipoService.actualizarEquipo(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar equipo por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEquipo(@PathVariable int id) {
        String resultado = equipoService.eliminarEquipo(id);
        return ResponseEntity.ok(resultado);
    }
}
