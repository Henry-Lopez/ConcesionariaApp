package com.consecionarioapp.controller;

import com.consecionarioapp.dto.VehiculoRequestDTO;
import com.consecionarioapp.service.VehiculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin("*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // ✅ Crear vehículo
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarVehiculo(@RequestBody VehiculoRequestDTO dto) {
        String resultado = vehiculoService.registrarVehiculo(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los vehículos
    @GetMapping("/listar")
    public ResponseEntity<List<VehiculoRequestDTO>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.listarVehiculos());
    }

    // ✅ Listar solo vehículos disponibles en stock
    @GetMapping("/disponibles")
    public ResponseEntity<List<VehiculoRequestDTO>> listarVehiculosDisponibles() {
        return ResponseEntity.ok(vehiculoService.listarVehiculosDisponibles());
    }

    // ✅ Obtener vehículo por número de chasis
    @GetMapping("/{nroChasis}")
    public ResponseEntity<VehiculoRequestDTO> obtenerVehiculoPorChasis(@PathVariable String nroChasis) {
        VehiculoRequestDTO dto = vehiculoService.obtenerVehiculoPorNroChasis(nroChasis);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar vehículo por número de chasis
    @PutMapping("/actualizar/{nroChasis}")
    public ResponseEntity<String> actualizarVehiculo(@PathVariable String nroChasis, @RequestBody VehiculoRequestDTO dto) {
        String resultado = vehiculoService.actualizarVehiculo(nroChasis, dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar vehículo por número de chasis
    @DeleteMapping("/eliminar/{nroChasis}")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable String nroChasis) {
        String resultado = vehiculoService.eliminarVehiculo(nroChasis);
        return ResponseEntity.ok(resultado);
    }
}
