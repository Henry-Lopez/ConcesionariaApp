package com.consecionarioapp.controller;

import com.consecionarioapp.dto.ModeloRequestDTO;
import com.consecionarioapp.service.ModeloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    private final ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    // ✅ Crear modelo
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarModelo(@RequestBody ModeloRequestDTO dto) {
        String resultado = modeloService.registrarModelo(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los modelos
    @GetMapping("/listar")
    public ResponseEntity<List<ModeloRequestDTO>> listarModelos() {
        return ResponseEntity.ok(modeloService.listarModelos());
    }

    // ✅ Obtener modelo por ID
    @GetMapping("/{id}")
    public ResponseEntity<ModeloRequestDTO> obtenerModeloPorId(@PathVariable int id) {
        ModeloRequestDTO dto = modeloService.obtenerModeloPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar modelo por ID
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarModelo(@PathVariable int id, @RequestBody ModeloRequestDTO dto) {
        String resultado = modeloService.actualizarModelo(id, dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar modelo por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarModelo(@PathVariable int id) {
        String resultado = modeloService.eliminarModelo(id);
        return ResponseEntity.ok(resultado);
    }
}
