package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.VentaRequestDTO;
import com.consecionarioapp.service.VentaService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    /* ═════════════ Helpers ═════════════ */
    private static boolean toBool(Object raw) {
        return (raw instanceof Boolean) ? (Boolean) raw
                : ((Number) raw).intValue() == 1; // tinyint(1)
    }

    private static Date toSqlDate(java.time.LocalDate ld) {
        return ld == null ? null : Date.valueOf(ld);
    }

    /* ═════════════ DI ═════════════ */
    @PersistenceContext
    private EntityManager entityManager;

    /* ╔══════════════════════════════════════╗
       ║ 1. REGISTRAR VENTA                   ║
       ╚══════════════════════════════════════╝ */
    @Override @Transactional
    public String registrarVenta(VentaRequestDTO dto) {

        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("registrar_venta")
                .registerStoredProcedureParameter("p_id_cliente"   , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_empleado"  , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_chasis"   , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_forma_pago"   , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_cuotas"       , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio_total" , BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_venta"  , Date.class      , ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_entrega", Date.class      , ParameterMode.IN)
                .registerStoredProcedureParameter("p_matricula"    , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_es_stock"     , Boolean.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_origen"       , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_generado"  , Integer.class   , ParameterMode.OUT)
                .registerStoredProcedureParameter("p_resultado"    , String.class    , ParameterMode.OUT);

        /* valores IN */
        q.setParameter("p_id_cliente"   , dto.getIdCliente());
        q.setParameter("p_id_empleado"  , dto.getIdEmpleado());
        q.setParameter("p_nro_chasis"   , dto.getNroChasis());
        q.setParameter("p_forma_pago"   , dto.getFormaPago());
        q.setParameter("p_cuotas"       , dto.getCuotas());
        q.setParameter("p_precio_total" , dto.getPrecioTotal());
        q.setParameter("p_fecha_venta"  , toSqlDate(dto.getFechaVenta()));
        q.setParameter("p_fecha_entrega", toSqlDate(dto.getFechaEntrega()));
        q.setParameter("p_matricula"    , dto.getMatricula());
        q.setParameter("p_es_stock"     , dto.isEsStock());
        q.setParameter("p_origen"       , dto.getOrigen());

        q.execute();

        String  resultado = (String)  q.getOutputParameterValue("p_resultado");
        Integer idVenta   = (Integer) q.getOutputParameterValue("p_id_generado");

        /* ─ Extras ─ */
        if (dto.getIdExtras() != null && !dto.getIdExtras().isEmpty()) {
            for (Integer idEquipo : dto.getIdExtras()) {
                BigDecimal precioExtra = (BigDecimal) entityManager
                        .createNativeQuery("SELECT precio FROM equipo WHERE id_equipo = ?")
                        .setParameter(1, idEquipo)
                        .getSingleResult();

                entityManager.createNativeQuery("""
                             INSERT INTO venta_extra(id_venta,id_equipo,precio_extra)
                             VALUES (?,?,?)""")
                        .setParameter(1, idVenta)
                        .setParameter(2, idEquipo)
                        .setParameter(3, precioExtra)
                        .executeUpdate();
            }
        }

        /* ─ Factura ─ */
        entityManager.createNativeQuery("CALL generar_factura_venta(?1)")
                .setParameter(1, idVenta)
                .executeUpdate();

        return resultado;
    }

    /* ╔══════════════════════════════════════╗
       ║ 2. LISTAR VENTAS                     ║
       ╚══════════════════════════════════════╝ */
    @Override @Transactional(readOnly = true)
    public List<VentaRequestDTO> listarVentas() {

        List<Object[]> rs  = entityManager.createNativeQuery("CALL listar_ventas()")
                .getResultList();
        List<VentaRequestDTO> out = new ArrayList<>();

        for (Object[] r : rs) {
            VentaRequestDTO v = new VentaRequestDTO();

            v.setIdVenta      ((Integer)    r[0]);
            v.setFechaVenta   (((Date)      r[1]).toLocalDate());
            v.setPrecioTotal  ((BigDecimal) r[2]);
            v.setFormaPago    ((String)     r[3]);
            v.setCuotas       ((Integer)    r[4]);
            v.setIdCliente    ((Integer)    r[5]);
            v.setIdEmpleado   ((Integer)    r[6]);
            v.setNroChasis    ((String)     r[7]);
            v.setMatricula    ((String)     r[8]);
            v.setEsStock      (toBool(r[9]));
            v.setOrigen       ((String)     r[10]);
            v.setFechaEntrega (((Date)      r[11]).toLocalDate());

            /* extras */
            String extrasCsv = (String) r[12];
            if (extrasCsv != null && !extrasCsv.isBlank()) {
                List<Integer> extras = List.of(extrasCsv.split(","))
                        .stream()
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());
                v.setIdExtras(extras);
            } else {
                v.setIdExtras(new ArrayList<>());
            }
            out.add(v);
        }
        return out;
    }

    /* ╔══════════════════════════════════════╗
       ║ 3. OBTENER POR ID                    ║
       ╚══════════════════════════════════════╝ */
    @Override @Transactional(readOnly = true)
    public VentaRequestDTO obtenerVentaPorId(int idVenta) {

        Object[] r = (Object[]) entityManager.createNativeQuery("""
            SELECT  v.id_venta,
                    v.fecha_venta,
                    v.precio_venta,
                    v.forma_pago,
                    v.cuotas,
                    v.id_cliente,
                    v.id_empleado,
                    ANY_VALUE(vv.nro_chasis),
                    ANY_VALUE(vv.matricula),
                    ANY_VALUE(vv.es_stock),
                    ANY_VALUE(vv.origen),
                    ANY_VALUE(vv.fecha_entrega),
                    IFNULL(GROUP_CONCAT(ve.id_equipo ORDER BY ve.id_equipo),'')
            FROM venta v
            JOIN venta_vehiculo vv USING(id_venta)
            LEFT JOIN venta_extra ve USING(id_venta)
            WHERE v.id_venta = ?1
            GROUP BY v.id_venta""")
                .setParameter(1, idVenta)
                .getSingleResult();

        VentaRequestDTO v = new VentaRequestDTO();

        v.setIdVenta      ((Integer)    r[0]);
        v.setFechaVenta   (((Date)      r[1]).toLocalDate());
        v.setPrecioTotal  ((BigDecimal) r[2]);
        v.setFormaPago    ((String)     r[3]);
        v.setCuotas       ((Integer)    r[4]);
        v.setIdCliente    ((Integer)    r[5]);
        v.setIdEmpleado   ((Integer)    r[6]);
        v.setNroChasis    ((String)     r[7]);
        v.setMatricula    ((String)     r[8]);
        v.setEsStock      (toBool(r[9]));
        v.setOrigen       ((String)     r[10]);
        v.setFechaEntrega (((Date)      r[11]).toLocalDate());

        String extrasCsv = (String) r[12];
        if (extrasCsv != null && !extrasCsv.isBlank()) {
            List<Integer> extras = List.of(extrasCsv.split(","))
                    .stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            v.setIdExtras(extras);
        } else {
            v.setIdExtras(new ArrayList<>());
        }
        return v;
    }

    /* ╔══════════════════════════════════════╗
       ║ 4. ACTUALIZAR VENTA                  ║
       ╚══════════════════════════════════════╝ */
    @Override @Transactional
    public String actualizarVenta(VentaRequestDTO dto) {

        String extrasCsv = dto.getIdExtras() == null ? ""
                : dto.getIdExtras().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));

        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("actualizar_venta")
                .registerStoredProcedureParameter("p_id_venta"     , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_venta"  , Date.class      , ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio_venta" , BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_forma_pago"   , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_cuotas"       , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente"   , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_empleado"  , Integer.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_chasis"   , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_entrega", Date.class      , ParameterMode.IN)
                .registerStoredProcedureParameter("p_matricula"    , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_es_stock"     , Boolean.class   , ParameterMode.IN)
                .registerStoredProcedureParameter("p_origen"       , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_extras_csv"   , String.class    , ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado"    , String.class    , ParameterMode.OUT);

        q.setParameter("p_id_venta"     , dto.getIdVenta());
        q.setParameter("p_fecha_venta"  , toSqlDate(dto.getFechaVenta()));
        q.setParameter("p_precio_venta" , dto.getPrecioTotal());
        q.setParameter("p_forma_pago"   , dto.getFormaPago());
        q.setParameter("p_cuotas"       , dto.getCuotas());
        q.setParameter("p_id_cliente"   , dto.getIdCliente());
        q.setParameter("p_id_empleado"  , dto.getIdEmpleado());
        q.setParameter("p_nro_chasis"   , dto.getNroChasis());
        q.setParameter("p_fecha_entrega", toSqlDate(dto.getFechaEntrega()));
        q.setParameter("p_matricula"    , dto.getMatricula());
        q.setParameter("p_es_stock"     , dto.isEsStock());
        q.setParameter("p_origen"       , dto.getOrigen());
        q.setParameter("p_extras_csv"   , extrasCsv);

        q.execute();
        return (String) q.getOutputParameterValue("p_resultado");
    }

    /* ╔══════════════════════════════════════╗
       ║ 5. ELIMINAR VENTA                    ║
       ╚══════════════════════════════════════╝ */
    @Override @Transactional
    public String eliminarVenta(int idVenta) {

        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("eliminar_venta")
                .registerStoredProcedureParameter("p_id_venta" , Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class , ParameterMode.OUT);

        q.setParameter("p_id_venta", idVenta);
        q.execute();
        return (String) q.getOutputParameterValue("p_resultado");
    }
}
