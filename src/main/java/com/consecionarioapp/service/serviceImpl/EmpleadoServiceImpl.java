package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.EmpleadoRequestDTO;
import com.consecionarioapp.service.EmpleadoService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarEmpleado(EmpleadoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_empleado")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_contratacion", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cargo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_trabaja_en", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_servicio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_apellido", dto.getApellido());
        query.setParameter("p_direccion", dto.getDireccion());
        query.setParameter("p_telefono", dto.getTelefono());
        query.setParameter("p_correo", dto.getCorreo());
        query.setParameter("p_fecha_contratacion", Date.valueOf(dto.getFechaContratacion()));
        query.setParameter("p_cargo", dto.getCargo());
        query.setParameter("p_trabaja_en", dto.getTrabajaEn());
        query.setParameter("p_id_concesionario", dto.getIdConcesionario());
        query.setParameter("p_id_servicio", dto.getIdServicio());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<EmpleadoRequestDTO> listarEmpleados() {
        List<EmpleadoRequestDTO> empleados = new ArrayList<>();
        try {
            List<Object[]> results = entityManager.createNativeQuery("CALL listar_empleados()").getResultList();

            for (Object[] row : results) {
                EmpleadoRequestDTO dto = new EmpleadoRequestDTO();
                dto.setIdEmpleado((Integer) row[0]);
                dto.setNombre((String) row[1]);
                dto.setApellido((String) row[2]);
                dto.setDireccion((String) row[3]);
                dto.setTelefono((String) row[4]);
                dto.setCorreo((String) row[5]);
                dto.setFechaContratacion(row[6] != null ? ((Date) row[6]).toLocalDate() : null);
                dto.setCargo((String) row[7]);
                dto.setTrabajaEn((String) row[8]);
                dto.setIdConcesionario(row[9] != null ? ((Number) row[9]).intValue() : null);
                dto.setIdServicio(row[10] != null ? ((Number) row[10]).intValue() : null);

                // 🆕 Nuevos campos si los devuelve el procedimiento
                if (row.length > 11) {
                    dto.setNombreConcesionario((String) row[11]);
                }
                if (row.length > 12) {
                    dto.setNombreServicio((String) row[12]);
                }

                empleados.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error al listar empleados: " + e.getMessage());
        }
        return empleados;
    }

    @Override
    @Transactional
    public String actualizarEmpleado(EmpleadoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_empleado")
                .registerStoredProcedureParameter("p_id_empleado", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_contratacion", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cargo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_trabaja_en", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_servicio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_empleado", dto.getIdEmpleado());
        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_apellido", dto.getApellido());
        query.setParameter("p_direccion", dto.getDireccion());
        query.setParameter("p_telefono", dto.getTelefono());
        query.setParameter("p_correo", dto.getCorreo());
        query.setParameter("p_fecha_contratacion", Date.valueOf(dto.getFechaContratacion()));
        query.setParameter("p_cargo", dto.getCargo());
        query.setParameter("p_trabaja_en", dto.getTrabajaEn());
        query.setParameter("p_id_concesionario", dto.getIdConcesionario());
        query.setParameter("p_id_servicio", dto.getIdServicio());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarEmpleado(int idEmpleado) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_empleado")
                .registerStoredProcedureParameter("p_id_empleado", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_empleado", idEmpleado);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public EmpleadoRequestDTO obtenerEmpleadoPorId(int idEmpleado) {
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, idEmpleado);

        List<Object[]> result = query.getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Empleado no encontrado");
        }

        Object[] row = result.get(0);
        EmpleadoRequestDTO dto = new EmpleadoRequestDTO();
        dto.setIdEmpleado((Integer) row[0]);
        dto.setNombre((String) row[1]);
        dto.setApellido((String) row[2]);
        dto.setDireccion((String) row[3]);
        dto.setTelefono((String) row[4]);
        dto.setCorreo((String) row[5]);
        dto.setFechaContratacion(row[6] != null ? ((Date) row[6]).toLocalDate() : null);
        dto.setCargo((String) row[7]);
        dto.setTrabajaEn((String) row[8]);
        dto.setIdConcesionario(row[9] != null ? ((Number) row[9]).intValue() : null);
        dto.setIdServicio(row[10] != null ? ((Number) row[10]).intValue() : null);

        return dto;
    }
}
