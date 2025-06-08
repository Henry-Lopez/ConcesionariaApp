package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.ConcesionarioRequestDTO;
import com.consecionarioapp.service.ConcesionarioService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConcesionarioServiceImpl implements ConcesionarioService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarConcesionario(ConcesionarioRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_concesionario")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_domicilio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nit", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_domicilio", dto.getDireccion());
        query.setParameter("p_nit", dto.getNit());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<ConcesionarioRequestDTO> listarConcesionarios() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_concesionarios()").getResultList();
        List<ConcesionarioRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            ConcesionarioRequestDTO dto = new ConcesionarioRequestDTO();
            dto.setIdConcesionario((Integer) row[0]);
            dto.setNombre((String) row[1]);
            dto.setDireccion((String) row[2]); // domicilio
            dto.setNit((String) row[3]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public ConcesionarioRequestDTO obtenerConcesionarioPorId(Integer idConcesionario) {
        String sql = "SELECT * FROM concesionario WHERE id_concesionario = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, idConcesionario);
        List<Object[]> result = query.getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Concesionario no encontrado");
        }

        Object[] row = result.get(0);
        ConcesionarioRequestDTO dto = new ConcesionarioRequestDTO();
        dto.setIdConcesionario((Integer) row[0]);
        dto.setNombre((String) row[1]);
        dto.setDireccion((String) row[2]); // domicilio
        dto.setNit((String) row[3]);

        return dto;
    }

    @Override
    @Transactional
    public String actualizarConcesionario(ConcesionarioRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_concesionario")
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_domicilio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nit", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_concesionario", dto.getIdConcesionario());
        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_domicilio", dto.getDireccion());
        query.setParameter("p_nit", dto.getNit());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarConcesionario(Integer idConcesionario) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_concesionario")
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_concesionario", idConcesionario);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }
}
