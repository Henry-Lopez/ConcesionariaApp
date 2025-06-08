package com.consecionarioapp.controller;

import com.consecionarioapp.dto.MecanicoRequestDTO;
import com.consecionarioapp.service.MecanicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mecanicos")
@CrossOrigin("*")
public class MecanicoController {

    private final MecanicoService mecanicoService;

    public MecanicoController(MecanicoService mecanicoService) {
        this.mecanicoService = mecanicoService;
    }

    // ✅ Crear mecánico
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMecanico(@RequestBody MecanicoRequestDTO dto) {
        String resultado = mecanicoService.registrarMecanico(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los mecánicos
    @GetMapping("/listar")
    public ResponseEntity<List<MecanicoRequestDTO>> listarMecanicos() {
        return ResponseEntity.ok(mecanicoService.listarMecanicos());
    }

    // ✅ Obtener mecánico por ID
    @GetMapping("/{id}")
    public ResponseEntity<MecanicoRequestDTO> obtenerMecanicoPorId(@PathVariable int id) {
        MecanicoRequestDTO dto = mecanicoService.obtenerMecanicoPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar mecánico
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarMecanico(@RequestBody MecanicoRequestDTO dto) {
        String resultado = mecanicoService.actualizarMecanico(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar mecánico por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarMecanico(@PathVariable int id) {
        String resultado = mecanicoService.eliminarMecanico(id);
        return ResponseEntity.ok(resultado);
    }
}
