package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.PedidoImportacionDTO;
import com.consecionarioapp.dto.PedidoResumenDTO;
import com.consecionarioapp.dto.VistaPedidoImportacionDTO;
import com.consecionarioapp.service.PedidoImportacionService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoImportacionServiceImpl implements PedidoImportacionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarPedido(PedidoImportacionDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_pedido_importacion")
                .registerStoredProcedureParameter("p_id_repuesto", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_solicitud", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_repuesto", dto.getIdRepuesto());
        query.setParameter("p_fecha_solicitud", Date.valueOf(dto.getFechaSolicitud()));

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<PedidoImportacionDTO> listarPedidos() {
        List<Object[]> rows = entityManager
                .createNativeQuery("CALL listar_pedidos_importacion()")
                .getResultList();

        List<PedidoImportacionDTO> lista = new ArrayList<>();

        for (Object[] row : rows) {
            PedidoImportacionDTO dto = new PedidoImportacionDTO();
            dto.setIdPedido((Integer) row[0]);
            dto.setIdRepuesto((Integer) row[1]);
            dto.setRepuesto((String) row[2]);
            dto.setFechaSolicitud(Date.valueOf(row[3].toString()).toLocalDate());
            dto.setEstado((String) row[4]);
            dto.setFechaEntrega(row[5] != null ? Date.valueOf(row[5].toString()).toLocalDate() : null);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public String actualizarPedido(PedidoImportacionDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_estado_pedido")
                .registerStoredProcedureParameter("p_id_pedido", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nuevo_estado", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_entrega", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_pedido", dto.getIdPedido());
        query.setParameter("p_nuevo_estado", dto.getEstado());
        query.setParameter("p_fecha_entrega", dto.getFechaEntrega() != null ? Date.valueOf(dto.getFechaEntrega()) : null);

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarPedido(Integer idPedido) {
        int deleted = entityManager
                .createNativeQuery("DELETE FROM pedido_importacion WHERE id_pedido = ?")
                .setParameter(1, idPedido)
                .executeUpdate();

        return deleted > 0 ? "✅ Pedido eliminado" : "⛔ No se encontró el pedido";
    }

    @Override
    @Transactional
    public PedidoImportacionDTO obtenerPedidoPorId(Integer idPedido) {
        Object[] row = (Object[]) entityManager
                .createNativeQuery("SELECT * FROM pedido_importacion WHERE id_pedido = ?")
                .setParameter(1, idPedido)
                .getSingleResult();

        PedidoImportacionDTO dto = new PedidoImportacionDTO();
        dto.setIdPedido((Integer) row[0]);
        dto.setIdRepuesto((Integer) row[1]);
        dto.setFechaSolicitud(Date.valueOf(row[2].toString()).toLocalDate());
        dto.setEstado((String) row[3]);
        dto.setFechaEntrega(row[4] != null ? Date.valueOf(row[4].toString()).toLocalDate() : null);

        return dto;
    }

    // ✅ Nuevo método para listar desde la vista
    @Override
    @Transactional
    public List<VistaPedidoImportacionDTO> listarVistaPedidos() {
        List<Object[]> resultado = entityManager
                .createNativeQuery("SELECT * FROM vista_pedidos_importacion")
                .getResultList();

        List<VistaPedidoImportacionDTO> lista = new ArrayList<>();

        for (Object[] fila : resultado) {
            VistaPedidoImportacionDTO dto = new VistaPedidoImportacionDTO();
            dto.setIdPedido((Integer) fila[0]);
            dto.setIdRepuesto((Integer) fila[1]);
            dto.setDescripcionRepuesto((String) fila[2]);
            dto.setEstado((String) fila[3]);
            dto.setFechaSolicitud(fila[4].toString());
            dto.setFechaEntrega(fila[5] != null ? fila[5].toString() : null);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public List<PedidoResumenDTO> obtenerResumenPedidos() {
        List<Object[]> resultados = entityManager.createNativeQuery("CALL reporte_resumen_pedidos_importacion()")
                .getResultList();

        List<PedidoResumenDTO> lista = new ArrayList<>();
        for (Object[] row : resultados) {
            PedidoResumenDTO dto = new PedidoResumenDTO();
            dto.setDescripcionRepuesto((String) row[0]);
            dto.setTotalPendientes(((Number) row[1]).intValue());
            dto.setTotalEntregados(((Number) row[2]).intValue());
            lista.add(dto);
        }

        return lista;
    }

}
