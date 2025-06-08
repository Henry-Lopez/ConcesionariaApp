package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.ServicioOficialRequestDTO;
import com.consecionarioapp.service.ServicioOficialService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioOficialServiceImpl implements ServicioOficialService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarServicio(ServicioOficialRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_servicio_oficial")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_domicilio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nit", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_domicilio", dto.getDomicilio());
        query.setParameter("p_nit", dto.getNit());
        query.setParameter("p_id_concesionario", dto.getIdConcesionario());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<ServicioOficialRequestDTO> listarServicios() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_servicios_oficiales()").getResultList();
        List<ServicioOficialRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            ServicioOficialRequestDTO dto = new ServicioOficialRequestDTO();
            dto.setIdServicio((Integer) row[0]);
            dto.setNombre((String) row[1]);
            dto.setDomicilio((String) row[2]);
            dto.setNit((String) row[3]);
            dto.setIdConcesionario((Integer) row[4]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public ServicioOficialRequestDTO obtenerPorId(Integer idServicio) {
        Object[] row = (Object[]) entityManager.createNativeQuery("SELECT * FROM servicio_oficial WHERE id_servicio = ?")
                .setParameter(1, idServicio)
                .getSingleResult();

        ServicioOficialRequestDTO dto = new ServicioOficialRequestDTO();
        dto.setIdServicio((Integer) row[0]);
        dto.setNombre((String) row[1]);
        dto.setDomicilio((String) row[2]);
        dto.setNit((String) row[3]);
        dto.setIdConcesionario((Integer) row[4]);
        return dto;
    }

    @Override
    @Transactional
    public String actualizarServicio(ServicioOficialRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_servicio_oficial")
                .registerStoredProcedureParameter("p_id_servicio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_domicilio", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nit", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_servicio", dto.getIdServicio());
        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_domicilio", dto.getDomicilio());
        query.setParameter("p_nit", dto.getNit());
        query.setParameter("p_id_concesionario", dto.getIdConcesionario());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarServicio(Integer idServicio) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_servicio_oficial")
                .registerStoredProcedureParameter("p_id_servicio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_servicio", idServicio);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }
}
