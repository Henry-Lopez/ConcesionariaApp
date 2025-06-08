package com.consecionarioapp.controller;

import com.consecionarioapp.dto.ClienteRequestDTO;
import com.consecionarioapp.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // ✅ Crear cliente
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarCliente(@RequestBody ClienteRequestDTO dto) {
        String resultado = clienteService.registrarCliente(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los clientes
    @GetMapping("/listar")
    public ResponseEntity<List<ClienteRequestDTO>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    // ✅ Actualizar cliente (el ID va dentro del DTO)
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizarCliente(@RequestBody ClienteRequestDTO dto) {
        String resultado = clienteService.actualizarCliente(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar cliente por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable int id) {
        String resultado = clienteService.eliminarCliente(id);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteRequestDTO> obtenerClientePorId(@PathVariable int id) {
        ClienteRequestDTO dto = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(dto);
    }
}
