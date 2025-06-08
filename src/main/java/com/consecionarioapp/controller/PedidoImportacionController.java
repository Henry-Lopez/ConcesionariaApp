package com.consecionarioapp.controller;

import com.consecionarioapp.dto.PedidoImportacionDTO;
import com.consecionarioapp.dto.PedidoResumenDTO;
import com.consecionarioapp.dto.VistaPedidoImportacionDTO;
import com.consecionarioapp.service.PedidoImportacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoImportacionController {

    private final PedidoImportacionService pedidoService;

    public PedidoImportacionController(PedidoImportacionService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // ✅ Registrar pedido
    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody PedidoImportacionDTO dto) {
        String resultado = pedidoService.registrarPedido(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Listar todos los pedidos
    @GetMapping("/listar")
    public ResponseEntity<List<PedidoImportacionDTO>> listar() {
        List<PedidoImportacionDTO> lista = pedidoService.listarPedidos();
        return ResponseEntity.ok(lista);
    }

    // ✅ Obtener pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoImportacionDTO> obtenerPorId(@PathVariable Integer id) {
        PedidoImportacionDTO dto = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(dto);
    }

    // ✅ Actualizar pedido
    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody PedidoImportacionDTO dto) {
        String resultado = pedidoService.actualizarPedido(dto);
        return ResponseEntity.ok(resultado);
    }

    // ✅ Eliminar pedido
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        String resultado = pedidoService.eliminarPedido(id);
        return ResponseEntity.ok(resultado);
    }

    // ✅ NUEVO: Listar pedidos desde la vista (con nombre del repuesto)
    @GetMapping("/vista")
    public ResponseEntity<List<VistaPedidoImportacionDTO>> listarVista() {
        List<VistaPedidoImportacionDTO> lista = pedidoService.listarVistaPedidos();
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/reporte-resumen")
    public ResponseEntity<List<PedidoResumenDTO>> obtenerResumenPedidos() {
        return ResponseEntity.ok(pedidoService.obtenerResumenPedidos());
    }

}
