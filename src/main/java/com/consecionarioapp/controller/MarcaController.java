package com.consecionarioapp.controller;

import com.consecionarioapp.dto.MarcaRequestDTO;
import com.consecionarioapp.service.MarcaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@CrossOrigin("*")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    // ✅ Crear marca
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMarca(@RequestBody MarcaRequestDTO dto) {
        String resultado = marcaService.registrarMarca(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todas las marcas
    @GetMapping("/listar")
    public ResponseEntity<List<MarcaRequestDTO>> listarMarcas() {
        return ResponseEntity.ok(marcaService.listarMarcas());
    }

    // ✅ Obtener marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<MarcaRequestDTO> obtenerMarcaPorId(@PathVariable int id) {
        MarcaRequestDTO dto = marcaService.obtenerMarcaPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar marca
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarMarca(@RequestBody MarcaRequestDTO dto) {
        String resultado = marcaService.actualizarMarca(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar marca por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarMarca(@PathVariable int id) {
        String resultado = marcaService.eliminarMarca(id);
        return ResponseEntity.ok(resultado);
    }
}
