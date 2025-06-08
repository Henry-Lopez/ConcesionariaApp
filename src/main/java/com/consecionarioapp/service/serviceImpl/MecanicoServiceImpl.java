package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.MecanicoRequestDTO;
import com.consecionarioapp.service.MecanicoService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class MecanicoServiceImpl implements MecanicoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarMecanico(MecanicoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_mecanico")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_especialidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_salario", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_ingreso", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_apellido", dto.getApellido());
        query.setParameter("p_direccion", dto.getDireccion());
        query.setParameter("p_telefono", dto.getTelefono());
        query.setParameter("p_especialidad", dto.getEspecialidad());
        query.setParameter("p_salario", dto.getSalario());
        query.setParameter("p_fecha_ingreso", Date.valueOf(dto.getFechaIngreso()));

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<MecanicoRequestDTO> listarMecanicos() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_mecanicos()").getResultList();
        List<MecanicoRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            MecanicoRequestDTO dto = new MecanicoRequestDTO();
            dto.setIdMecanico((Integer) row[0]);
            dto.setNombre((String) row[1]);
            dto.setApellido((String) row[2]);
            dto.setDireccion((String) row[3]);
            dto.setTelefono((String) row[4]);
            dto.setEspecialidad((String) row[5]);
            dto.setSalario((java.math.BigDecimal) row[6]);
            dto.setFechaIngreso(((Date) row[7]).toLocalDate());
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public String actualizarMecanico(MecanicoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_mecanico")
                .registerStoredProcedureParameter("p_id_mecanico", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_especialidad", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_salario", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_ingreso", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_mecanico", dto.getIdMecanico());
        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_apellido", dto.getApellido());
        query.setParameter("p_direccion", dto.getDireccion());
        query.setParameter("p_telefono", dto.getTelefono());
        query.setParameter("p_especialidad", dto.getEspecialidad());
        query.setParameter("p_salario", dto.getSalario());
        query.setParameter("p_fecha_ingreso", Date.valueOf(dto.getFechaIngreso()));

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarMecanico(Integer idMecanico) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_mecanico")
                .registerStoredProcedureParameter("p_id_mecanico", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_mecanico", idMecanico);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public MecanicoRequestDTO obtenerMecanicoPorId(Integer idMecanico) {
        List<Object[]> result = entityManager
                .createNativeQuery("SELECT * FROM mecanico WHERE id_mecanico = ?1")
                .setParameter(1, idMecanico)
                .getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Mecánico no encontrado");
        }

        Object[] row = result.get(0);
        MecanicoRequestDTO dto = new MecanicoRequestDTO();
        dto.setIdMecanico((Integer) row[0]);
        dto.setNombre((String) row[1]);
        dto.setApellido((String) row[2]);
        dto.setDireccion((String) row[3]);
        dto.setTelefono((String) row[4]);
        dto.setEspecialidad((String) row[5]);
        dto.setSalario((java.math.BigDecimal) row[6]);
        dto.setFechaIngreso(((Date) row[7]).toLocalDate());

        return dto;
    }
}
