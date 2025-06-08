package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.FinanciamientoRequestDTO;
import com.consecionarioapp.service.FinanciamientoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinanciamientoServiceImpl implements FinanciamientoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarFinanciamiento(FinanciamientoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_financiamiento")
                .registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_entidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_monto", Double.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cuotas", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_interes", Double.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_plazo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_venta", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_tipo", dto.getTipoFinanciamiento());
        query.setParameter("p_entidad", dto.getEntidadFinanciera());
        query.setParameter("p_monto", dto.getMonto().doubleValue());
        query.setParameter("p_cuotas", dto.getCuotas());
        query.setParameter("p_interes", dto.getTasaInteres().doubleValue());
        query.setParameter("p_plazo", dto.getPlazo());
        query.setParameter("p_id_venta", dto.getIdVenta());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<FinanciamientoRequestDTO> listarFinanciamientos() {
        List<Object[]> resultados = entityManager
                .createNativeQuery("CALL listar_financiamientos()")
                .getResultList();

        List<FinanciamientoRequestDTO> lista = new ArrayList<>();
        for (Object[] row : resultados) {
            FinanciamientoRequestDTO dto = new FinanciamientoRequestDTO();
            dto.setIdFinanciamiento((Integer) row[0]);
            dto.setTipoFinanciamiento((String) row[1]);
            dto.setEntidadFinanciera((String) row[2]);
            dto.setMonto((BigDecimal) row[3]);
            dto.setCuotas((Integer) row[4]);
            dto.setTasaInteres((BigDecimal) row[5]);
            dto.setPlazo((Integer) row[6]);
            dto.setIdVenta((Integer) row[7]);
            dto.setClienteNombre((String) row[8]);
            dto.setClienteApellido((String) row[9]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public FinanciamientoRequestDTO obtenerFinanciamientoPorId(int idFinanciamiento) {
        Object[] row = (Object[]) entityManager
                .createNativeQuery("SELECT * FROM financiamiento WHERE id_financiamiento = ?")
                .setParameter(1, idFinanciamiento)
                .getSingleResult();

        FinanciamientoRequestDTO dto = new FinanciamientoRequestDTO();
        dto.setIdFinanciamiento((Integer) row[0]);
        dto.setTipoFinanciamiento((String) row[1]);
        dto.setEntidadFinanciera((String) row[2]);
        dto.setMonto((BigDecimal) row[3]);
        dto.setCuotas((Integer) row[4]);
        dto.setTasaInteres((BigDecimal) row[5]);
        dto.setPlazo((Integer) row[6]);
        dto.setIdVenta((Integer) row[7]);
        return dto;
    }

    @Override
    @Transactional
    public FinanciamientoRequestDTO obtenerPorVenta(int idVenta) {
        List<Object[]> rows = entityManager
                .createNativeQuery("SELECT * FROM financiamiento WHERE id_venta = ?")
                .setParameter(1, idVenta)
                .getResultList();

        if (rows.isEmpty()) return null;

        Object[] row = rows.get(0);
        FinanciamientoRequestDTO dto = new FinanciamientoRequestDTO();
        dto.setIdFinanciamiento((Integer) row[0]);
        dto.setTipoFinanciamiento((String) row[1]);
        dto.setEntidadFinanciera((String) row[2]);
        dto.setMonto((BigDecimal) row[3]);
        dto.setCuotas((Integer) row[4]);
        dto.setTasaInteres((BigDecimal) row[5]);
        dto.setPlazo((Integer) row[6]);
        dto.setIdVenta((Integer) row[7]);
        return dto;
    }

    @Override
    @Transactional
    public String actualizarFinanciamiento(FinanciamientoRequestDTO dto) {
        int filas = entityManager.createNativeQuery("""
                UPDATE financiamiento SET
                    tipo_financiamiento = ?,
                    entidad_financiera = ?,
                    monto = ?,
                    cuotas = ?,
                    tasa_interes = ?,
                    plazo = ?
                WHERE id_financiamiento = ?
            """)
                .setParameter(1, dto.getTipoFinanciamiento())
                .setParameter(2, dto.getEntidadFinanciera())
                .setParameter(3, dto.getMonto())
                .setParameter(4, dto.getCuotas())
                .setParameter(5, dto.getTasaInteres())
                .setParameter(6, dto.getPlazo())
                .setParameter(7, dto.getIdFinanciamiento())
                .executeUpdate();

        return filas > 0 ? "✅ Financiamiento actualizado correctamente" : "⚠️ No se encontró el financiamiento";
    }

    @Override
    @Transactional
    public String eliminarFinanciamiento(int idFinanciamiento) {
        int filas = entityManager
                .createNativeQuery("DELETE FROM financiamiento WHERE id_financiamiento = ?")
                .setParameter(1, idFinanciamiento)
                .executeUpdate();

        return filas > 0 ? "🗑️ Financiamiento eliminado" : "❌ No se encontró el financiamiento";
    }

    // ✅ NUEVO MÉTODO: listar financiamientos sin banco
    @Override
    @Transactional
    public List<FinanciamientoRequestDTO> listarSinBanco() {
        List<FinanciamientoRequestDTO> lista = new ArrayList<>();
        List<Object[]> resultados = entityManager
                .createNativeQuery("CALL listar_financiamientos_disponibles()")
                .getResultList();

        for (Object[] row : resultados) {
            FinanciamientoRequestDTO dto = new FinanciamientoRequestDTO();
            dto.setIdFinanciamiento((Integer) row[0]);
            dto.setTipoFinanciamiento((String) row[1]);
            dto.setEntidadFinanciera((String) row[2]);
            dto.setMonto((BigDecimal) row[3]);
            dto.setCuotas((Integer) row[4]);
            dto.setTasaInteres((BigDecimal) row[5]);
            dto.setPlazo((Integer) row[6]);
            dto.setIdVenta((Integer) row[7]);
            lista.add(dto);
        }

        return lista;
    }
}
