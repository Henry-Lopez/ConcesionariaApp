package com.consecionarioapp.controller;

import com.consecionarioapp.dto.ServicioOficialRequestDTO;
import com.consecionarioapp.service.ServicioOficialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/servicios")
public class ServicioOficialController {

    private final ServicioOficialService servicioOficialService;

    public ServicioOficialController(ServicioOficialService servicioOficialService) {
        this.servicioOficialService = servicioOficialService;
    }

    // ✅ Crear servicio oficial
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarServicio(@RequestBody ServicioOficialRequestDTO dto) {
        String resultado = servicioOficialService.registrarServicio(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los servicios oficiales
    @GetMapping("/listar")
    public ResponseEntity<List<ServicioOficialRequestDTO>> listarServicios() {
        return ResponseEntity.ok(servicioOficialService.listarServicios());
    }

    // ✅ Obtener servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioOficialRequestDTO> obtenerPorId(@PathVariable int id) {
        ServicioOficialRequestDTO dto = servicioOficialService.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar servicio oficial
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody ServicioOficialRequestDTO dto) {
        dto.setIdServicio(id); // aseguramos que el ID venga correcto
        String resultado = servicioOficialService.actualizarServicio(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar servicio oficial
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        String resultado = servicioOficialService.eliminarServicio(id);
        return ResponseEntity.ok(resultado);
    }
}
