package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.RepuestoRequestDTO;
import com.consecionarioapp.service.RepuestoService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepuestoServiceImpl implements RepuestoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarRepuesto(RepuestoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_repuesto")
                .registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio_unidad", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_descripcion", dto.getDescripcion());
        query.setParameter("p_precio_unidad", dto.getPrecioUnidad());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<RepuestoRequestDTO> listarRepuestos() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_repuestos()").getResultList();
        List<RepuestoRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            RepuestoRequestDTO dto = new RepuestoRequestDTO();
            dto.setIdRepuesto((Integer) row[0]);
            dto.setDescripcion((String) row[1]);
            dto.setPrecioUnidad((java.math.BigDecimal) row[2]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public String actualizarRepuesto(int idRepuesto, RepuestoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_repuesto")
                .registerStoredProcedureParameter("p_id_repuesto", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio_unidad", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_repuesto", idRepuesto);
        query.setParameter("p_descripcion", dto.getDescripcion());
        query.setParameter("p_precio_unidad", dto.getPrecioUnidad());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarRepuesto(int idRepuesto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_repuesto")
                .registerStoredProcedureParameter("p_id_repuesto", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_repuesto", idRepuesto);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public RepuestoRequestDTO obtenerRepuestoPorId(int idRepuesto) {
        List<Object[]> result = entityManager
                .createNativeQuery("SELECT * FROM repuesto WHERE id_repuesto = ?1")
                .setParameter(1, idRepuesto)
                .getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Repuesto no encontrado");
        }

        Object[] row = result.get(0);
        RepuestoRequestDTO dto = new RepuestoRequestDTO();
        dto.setIdRepuesto((Integer) row[0]);
        dto.setDescripcion((String) row[1]);
        dto.setPrecioUnidad((java.math.BigDecimal) row[2]);

        return dto;
    }
}
