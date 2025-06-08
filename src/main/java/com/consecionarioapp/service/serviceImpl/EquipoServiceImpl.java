package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.EquipoRequestDTO;
import com.consecionarioapp.service.EquipoService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipoServiceImpl implements EquipoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarEquipo(EquipoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_equipo")
                .registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_descripcion", dto.getDescripcion());
        query.setParameter("p_precio", dto.getPrecio());
        query.execute();

        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<EquipoRequestDTO> listarEquipos() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_equipos()").getResultList();
        List<EquipoRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            EquipoRequestDTO dto = new EquipoRequestDTO();
            dto.setIdEquipo((Integer) row[0]);
            dto.setDescripcion((String) row[1]);
            dto.setPrecio((java.math.BigDecimal) row[2]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public EquipoRequestDTO obtenerEquipoPorId(Integer idEquipo) {
        List<Object[]> result = entityManager
                .createNativeQuery("SELECT * FROM equipo WHERE id_equipo = ?1")
                .setParameter(1, idEquipo)
                .getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Equipo no encontrado");
        }

        Object[] row = result.get(0);
        EquipoRequestDTO dto = new EquipoRequestDTO();
        dto.setIdEquipo((Integer) row[0]);
        dto.setDescripcion((String) row[1]);
        dto.setPrecio((java.math.BigDecimal) row[2]);

        return dto;
    }

    @Override
    @Transactional
    public String actualizarEquipo(EquipoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_equipo")
                .registerStoredProcedureParameter("p_id_equipo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_equipo", dto.getIdEquipo());
        query.setParameter("p_descripcion", dto.getDescripcion());
        query.setParameter("p_precio", dto.getPrecio());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarEquipo(Integer idEquipo) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_equipo")
                .registerStoredProcedureParameter("p_id_equipo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_equipo", idEquipo);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }
}
