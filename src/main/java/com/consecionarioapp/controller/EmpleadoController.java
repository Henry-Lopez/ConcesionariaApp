package com.consecionarioapp.controller;

import com.consecionarioapp.dto.EmpleadoRequestDTO;
import com.consecionarioapp.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin("*")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // ✅ Crear empleado
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarEmpleado(@RequestBody EmpleadoRequestDTO dto) {
        String resultado = empleadoService.registrarEmpleado(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los empleados
    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoRequestDTO>> listarEmpleados() {
        return ResponseEntity.ok(empleadoService.listarEmpleados());
    }

    // ✅ Actualizar empleado
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarEmpleado(@RequestBody EmpleadoRequestDTO dto) {
        String resultado = empleadoService.actualizarEmpleado(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar empleado por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable int id) {
        String resultado = empleadoService.eliminarEmpleado(id);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Obtener empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoRequestDTO> obtenerEmpleadoPorId(@PathVariable int id) {
        EmpleadoRequestDTO dto = empleadoService.obtenerEmpleadoPorId(id);
        return ResponseEntity.ok(dto);
    }
}
