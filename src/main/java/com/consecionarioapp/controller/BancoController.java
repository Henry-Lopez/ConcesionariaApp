package com.consecionarioapp.controller;

import com.consecionarioapp.dto.BancoRequestDTO;
import com.consecionarioapp.service.BancoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/bancos")
public class BancoController {

    private final BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    // ✅ Registrar banco
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarBanco(@RequestBody BancoRequestDTO dto) {
        String resultado = bancoService.registrarBanco(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar bancos
    @GetMapping("/listar")
    public ResponseEntity<List<BancoRequestDTO>> listarBancos() {
        return ResponseEntity.ok(bancoService.listarBancos());
    }

    // ✅ Obtener banco por ID
    @GetMapping("/{id}")
    public ResponseEntity<BancoRequestDTO> obtenerBancoPorId(@PathVariable int id) {
        return ResponseEntity.ok(bancoService.obtenerBancoPorId(id));
    }

    // ✅ Actualizar banco
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarBanco(@RequestBody BancoRequestDTO dto) {
        String resultado = bancoService.actualizarBanco(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar banco
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarBanco(@PathVariable int id) {
        String resultado = bancoService.eliminarBanco(id);
        return ResponseEntity.ok(resultado);
    }
}
