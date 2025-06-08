package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.MarcaRequestDTO;
import com.consecionarioapp.service.MarcaService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarMarca(MarcaRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_marca")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.execute();

        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<MarcaRequestDTO> listarMarcas() {
        List<Object[]> results = entityManager
                .createNativeQuery("CALL listar_marcas()")
                .getResultList();

        List<MarcaRequestDTO> lista = new ArrayList<>();
        for (Object[] row : results) {
            MarcaRequestDTO dto = new MarcaRequestDTO();
            dto.setIdMarca((Integer) row[0]);
            dto.setNombre((String) row[1]);
            lista.add(dto);
        }
        return lista;
    }

    @Override
    @Transactional
    public MarcaRequestDTO obtenerMarcaPorId(Integer idMarca) {
        List<Object[]> result = entityManager
                .createNativeQuery("SELECT * FROM marca WHERE id_marca = ?1")
                .setParameter(1, idMarca)
                .getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Marca no encontrada");
        }

        Object[] row = result.get(0);
        MarcaRequestDTO dto = new MarcaRequestDTO();
        dto.setIdMarca((Integer) row[0]);
        dto.setNombre((String) row[1]);

        return dto;
    }

    @Override
    @Transactional
    public String actualizarMarca(MarcaRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_marca")
                .registerStoredProcedureParameter("p_id_marca", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_marca", dto.getIdMarca());
        query.setParameter("p_nombre", dto.getNombre());
        query.execute();

        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarMarca(Integer idMarca) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_marca")
                .registerStoredProcedureParameter("p_id_marca", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_marca", idMarca);
        query.execute();

        return (String) query.getOutputParameterValue("p_resultado");
    }
}
