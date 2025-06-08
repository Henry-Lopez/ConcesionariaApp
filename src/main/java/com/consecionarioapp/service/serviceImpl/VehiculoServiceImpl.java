package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.VehiculoRequestDTO;
import com.consecionarioapp.service.VehiculoService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarVehiculo(VehiculoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_vehiculo")
                .registerStoredProcedureParameter("p_nro_chasis", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_modelo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_anio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_estado", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_en_stock", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_ubicacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_servicio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nro_chasis", dto.getNroChasis());
        query.setParameter("p_id_modelo", dto.getIdModelo());
        query.setParameter("p_color", dto.getColor());
        query.setParameter("p_anio", dto.getAnio());
        query.setParameter("p_precio", dto.getPrecio());
        query.setParameter("p_estado", dto.getEstado());
        query.setParameter("p_en_stock", dto.isEnStock());
        query.setParameter("p_ubicacion", dto.getUbicacion());
        query.setParameter("p_id_concesionario", dto.getIdConcesionario());
        query.setParameter("p_id_servicio", dto.getIdServicio());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<VehiculoRequestDTO> listarVehiculos() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_vehiculos()").getResultList();
        List<VehiculoRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            VehiculoRequestDTO dto = new VehiculoRequestDTO();
            dto.setNroChasis((String) row[0]);
            dto.setIdModelo((Integer) row[1]);
            dto.setColor((String) row[2]);
            dto.setAnio(((java.sql.Date) row[3]).toLocalDate().getYear());
            dto.setPrecio((java.math.BigDecimal) row[4]);
            dto.setEstado((String) row[5]);
            dto.setEnStock(Boolean.parseBoolean(row[6].toString()));
            dto.setUbicacion((String) row[7]);
            dto.setIdConcesionario((Integer) row[8]);
            dto.setIdServicio((Integer) row[9]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public List<VehiculoRequestDTO> listarVehiculosDisponibles() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_vehiculos_disponibles()").getResultList();
        List<VehiculoRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            VehiculoRequestDTO dto = new VehiculoRequestDTO();
            dto.setNroChasis((String) row[0]);
            dto.setIdModelo((Integer) row[1]);
            dto.setColor((String) row[2]);
            dto.setAnio(((java.sql.Date) row[3]).toLocalDate().getYear());
            dto.setPrecio((java.math.BigDecimal) row[4]);
            dto.setEstado((String) row[5]);
            dto.setEnStock(Boolean.parseBoolean(row[6].toString()));
            dto.setUbicacion((String) row[7]);
            dto.setIdConcesionario((Integer) row[8]);
            dto.setIdServicio((Integer) row[9]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public String actualizarVehiculo(String nroChasis, VehiculoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_vehiculo")
                .registerStoredProcedureParameter("p_nro_chasis", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_modelo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_anio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio", java.math.BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_estado", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_en_stock", Boolean.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_ubicacion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_concesionario", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_servicio", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nro_chasis", nroChasis);
        query.setParameter("p_id_modelo", dto.getIdModelo());
        query.setParameter("p_color", dto.getColor());
        query.setParameter("p_anio", dto.getAnio());
        query.setParameter("p_precio", dto.getPrecio());
        query.setParameter("p_estado", dto.getEstado());
        query.setParameter("p_en_stock", dto.isEnStock());
        query.setParameter("p_ubicacion", dto.getUbicacion());
        query.setParameter("p_id_concesionario", dto.getIdConcesionario());
        query.setParameter("p_id_servicio", dto.getIdServicio());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarVehiculo(String nroChasis) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_vehiculo")
                .registerStoredProcedureParameter("p_nro_chasis", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nro_chasis", nroChasis);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public VehiculoRequestDTO obtenerVehiculoPorNroChasis(String nroChasis) {
        List<Object[]> result = entityManager
                .createNativeQuery("SELECT * FROM vehiculo WHERE nro_chasis = ?1")
                .setParameter(1, nroChasis)
                .getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Vehículo no encontrado");
        }

        Object[] row = result.get(0);
        VehiculoRequestDTO dto = new VehiculoRequestDTO();
        dto.setNroChasis((String) row[0]);
        dto.setIdModelo((Integer) row[1]);
        dto.setColor((String) row[2]);
        dto.setAnio(((java.sql.Date) row[3]).toLocalDate().getYear());
        dto.setPrecio((java.math.BigDecimal) row[4]);
        dto.setEstado((String) row[5]);
        dto.setEnStock((Boolean) row[6]);
        dto.setUbicacion((String) row[7]);
        dto.setIdConcesionario((Integer) row[8]);
        dto.setIdServicio((Integer) row[9]);

        return dto;
    }

}
