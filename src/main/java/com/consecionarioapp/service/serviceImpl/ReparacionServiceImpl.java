package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.ManoObraDTO;
import com.consecionarioapp.dto.ReparacionRequestDTO;
import com.consecionarioapp.service.ReparacionService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReparacionServiceImpl implements ReparacionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarReparacion(ReparacionRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_reparacion")
                .registerStoredProcedureParameter("p_fecha_entrada", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_hora_entrada", Time.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_vehiculo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_diagnostico", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_garantia", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_mano_obra", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_reparacion", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_fecha_entrada", Date.valueOf(dto.getFechaEntrada()));
        query.setParameter("p_hora_entrada", Time.valueOf(dto.getHoraEntrada()));
        query.setParameter("p_id_cliente", dto.getIdCliente());
        query.setParameter("p_id_vehiculo", dto.getNroChasis());
        query.setParameter("p_descripcion", dto.getDescripcion());
        query.setParameter("p_diagnostico", dto.getDiagnostico());
        query.setParameter("p_garantia", dto.getGarantia());
        query.setParameter("p_mano_obra", dto.getManoObraJson());

        query.execute();

        Integer idReparacion = (Integer) query.getOutputParameterValue("p_id_reparacion");
        String resultado = (String) query.getOutputParameterValue("p_resultado");

        // Mecánicos auxiliares
        for (Integer idMec : dto.getIdMecanicos()) {
            entityManager.createNativeQuery("INSERT INTO reparacion_mecanico (id_reparacion, id_mecanico) VALUES (?, ?)")
                    .setParameter(1, idReparacion)
                    .setParameter(2, idMec)
                    .executeUpdate();
        }

        // Repuestos
        for (Integer idRep : dto.getIdRepuestos()) {
            entityManager.createNativeQuery("INSERT INTO reparacion_repuesto (id_reparacion, id_repuesto) VALUES (?, ?)")
                    .setParameter(1, idReparacion)
                    .setParameter(2, idRep)
                    .executeUpdate();
        }

        return resultado;
    }

    @Override
    @Transactional
    public List<ReparacionRequestDTO> listarReparaciones() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_reparaciones()").getResultList();
        List<ReparacionRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            ReparacionRequestDTO dto = new ReparacionRequestDTO();
            dto.setIdReparacion((Integer) row[0]);
            dto.setIdCliente((Integer) row[1]);
            dto.setNroChasis((String) row[3]);
            dto.setDescripcion((String) row[4]);
            dto.setDiagnostico((String) row[5]);
            dto.setGarantia((String) row[6]);

            // ✅ Índices corregidos para evitar errores de tipo
            dto.setFechaEntrada(LocalDate.parse(row[9].toString()));
            dto.setHoraEntrada(LocalTime.parse(row[10].toString()));

            // Mecánicos
            List<Integer> mecanicos = entityManager.createNativeQuery(
                            "SELECT id_mecanico FROM reparacion_mecanico WHERE id_reparacion = ?")
                    .setParameter(1, dto.getIdReparacion())
                    .getResultList();
            dto.setIdMecanicos(mecanicos);

            // Repuestos
            List<Integer> repuestos = entityManager.createNativeQuery(
                            "SELECT id_repuesto FROM reparacion_repuesto WHERE id_reparacion = ?")
                    .setParameter(1, dto.getIdReparacion())
                    .getResultList();
            dto.setIdRepuestos(repuestos);

            // Mano de obra
            List<Object[]> rawManoObra = entityManager.createNativeQuery(
                            "SELECT id_mecanico, horas_trabajadas, costo_hora FROM mano_obra WHERE id_reparacion = ?")
                    .setParameter(1, dto.getIdReparacion())
                    .getResultList();

            List<ManoObraDTO> manoObraList = new ArrayList<>();
            for (Object[] mo : rawManoObra) {
                ManoObraDTO moDto = new ManoObraDTO();
                moDto.setIdMecanico((Integer) mo[0]);
                moDto.setHorasTrabajadas(((Number) mo[1]).doubleValue());
                moDto.setCostoHora(((Number) mo[2]).doubleValue());
                manoObraList.add(moDto);
            }
            dto.setManoObra(manoObraList);

            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public String actualizarReparacion(int idReparacion, ReparacionRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_reparacion_base")
                .registerStoredProcedureParameter("p_id_reparacion", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_entrada", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_hora_entrada", Time.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_chasis", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_diagnostico", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_garantia", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_mano_obra", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_reparacion", idReparacion);
        query.setParameter("p_fecha_entrada", Date.valueOf(dto.getFechaEntrada()));
        query.setParameter("p_hora_entrada", Time.valueOf(dto.getHoraEntrada()));
        query.setParameter("p_descripcion", dto.getDescripcion());
        query.setParameter("p_id_cliente", dto.getIdCliente());
        query.setParameter("p_nro_chasis", dto.getNroChasis());
        query.setParameter("p_diagnostico", dto.getDiagnostico());
        query.setParameter("p_garantia", dto.getGarantia());
        query.setParameter("p_mano_obra", dto.getManoObraJson());

        query.execute();

        // Reasignar mecánicos y repuestos
        entityManager.createNativeQuery("DELETE FROM reparacion_mecanico WHERE id_reparacion = ?")
                .setParameter(1, idReparacion).executeUpdate();
        entityManager.createNativeQuery("DELETE FROM reparacion_repuesto WHERE id_reparacion = ?")
                .setParameter(1, idReparacion).executeUpdate();

        for (Integer idMec : dto.getIdMecanicos()) {
            entityManager.createNativeQuery("INSERT INTO reparacion_mecanico (id_reparacion, id_mecanico) VALUES (?, ?)")
                    .setParameter(1, idReparacion)
                    .setParameter(2, idMec)
                    .executeUpdate();
        }

        for (Integer idRep : dto.getIdRepuestos()) {
            entityManager.createNativeQuery("INSERT INTO reparacion_repuesto (id_reparacion, id_repuesto) VALUES (?, ?)")
                    .setParameter(1, idReparacion)
                    .setParameter(2, idRep)
                    .executeUpdate();
        }

        entityManager.createNativeQuery("CALL generar_factura_reparacion(?)")
                .setParameter(1, idReparacion)
                .executeUpdate();

        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarReparacion(int idReparacion) {
        entityManager.createNativeQuery("DELETE FROM mano_obra WHERE id_reparacion = ?")
                .setParameter(1, idReparacion).executeUpdate();

        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_reparacion")
                .registerStoredProcedureParameter("p_id_reparacion", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_reparacion", idReparacion);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public ReparacionRequestDTO obtenerReparacionPorId(int idReparacion) {
        Object[] row = (Object[]) entityManager
                .createNativeQuery("SELECT * FROM reparacion WHERE id_reparacion = ?")
                .setParameter(1, idReparacion)
                .getSingleResult();

        ReparacionRequestDTO dto = new ReparacionRequestDTO();
        dto.setIdReparacion((Integer) row[0]);

        // ✅ Manejo seguro con parseo
        dto.setFechaEntrada(LocalDate.parse(row[1].toString()));
        dto.setHoraEntrada(LocalTime.parse(row[2].toString()));

        dto.setDescripcion((String) row[3]);
        dto.setIdCliente((Integer) row[4]);
        dto.setNroChasis((String) row[5]);
        dto.setDiagnostico((String) row[6]);
        dto.setGarantia((String) row[7]);

        List<Integer> mecanicos = entityManager
                .createNativeQuery("SELECT id_mecanico FROM reparacion_mecanico WHERE id_reparacion = ?")
                .setParameter(1, idReparacion)
                .getResultList();
        dto.setIdMecanicos(mecanicos);

        List<Integer> repuestos = entityManager
                .createNativeQuery("SELECT id_repuesto FROM reparacion_repuesto WHERE id_reparacion = ?")
                .setParameter(1, idReparacion)
                .getResultList();
        dto.setIdRepuestos(repuestos);

        return dto;
    }

}
