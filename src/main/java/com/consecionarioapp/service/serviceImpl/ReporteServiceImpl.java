package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.*;
import com.consecionarioapp.service.ReporteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ReporteVentasPorMesDTO> obtenerVentasPorMes(int mes, int anio) {
        Query query = entityManager.createNativeQuery("CALL reporte_ventas_por_mes(:mes, :anio)");
        query.setParameter("mes", mes);
        query.setParameter("anio", anio);

        List<Object[]> results = query.getResultList();
        List<ReporteVentasPorMesDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new ReporteVentasPorMesDTO(
                    (String) row[0],
                    ((Number) row[1]).intValue(),
                    ((Number) row[2]).doubleValue()
            ));
        }
        return reportes;
    }

    @Override
    public List<ReporteVentasPorEmpleadoDTO> obtenerVentasPorEmpleado() {
        Query query = entityManager.createNativeQuery("CALL reporte_ventas_por_empleado()");
        List<Object[]> results = query.getResultList();
        List<ReporteVentasPorEmpleadoDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new ReporteVentasPorEmpleadoDTO(
                    (String) row[0], // nombre
                    (String) row[1], // apellido
                    ((Number) row[2]).intValue(), // cantidad_ventas
                    ((Number) row[3]).doubleValue() // total_generado
            ));
        }
        return reportes;
    }

    @Override
    public List<ReporteIngresosTotalesDTO> obtenerIngresosTotales() {
        Query query = entityManager.createNativeQuery("CALL reporte_ingresos_totales()");
        List<Object[]> results = query.getResultList();
        List<ReporteIngresosTotalesDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new ReporteIngresosTotalesDTO(
                    (String) row[0],
                    ((Number) row[1]).intValue(), // ← cambio aquí
                    ((Number) row[2]).intValue(), // ← y aquí
                    (BigDecimal) row[3]
            ));
        }
        return reportes;
    }


    @Override
    public List<ReporteModelosMasVendidosDTO> obtenerModelosMasVendidos() {
        Query query = entityManager.createNativeQuery("CALL reporte_modelos_mas_vendidos()");
        List<Object[]> results = query.getResultList();
        List<ReporteModelosMasVendidosDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new ReporteModelosMasVendidosDTO(
                    (String) row[0],            // nombre_modelo
                    (Long) row[1],           // unidades_vendidas
                    (BigDecimal) row[2]         // total_generado
            ));
        }
        return reportes;
    }


    @Override
    public List<ReporteReparacionesPorMecanicoDTO> obtenerReparacionesPorMecanico() {
        Query query = entityManager.createNativeQuery("CALL reporte_reparaciones_por_mecanico()");
        List<Object[]> results = query.getResultList();
        List<ReporteReparacionesPorMecanicoDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new ReporteReparacionesPorMecanicoDTO(
                    (String) row[1], // nombre
                    (String) row[2], // apellido
                    ((Number) row[3]).intValue(), // cantidad reparaciones
                    ((Number) row[4]).doubleValue() // total generado
            ));
        }
        return reportes;
    }


    @Override
    public List<ReporteRepuestosMasUsadosDTO> obtenerRepuestosMasUsados() {
        Query query = entityManager.createNativeQuery("CALL reporte_repuestos_mas_usados()");
        List<Object[]> results = query.getResultList();
        List<ReporteRepuestosMasUsadosDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new ReporteRepuestosMasUsadosDTO(
                    (String) row[1],                         // descripcion
                    ((Number) row[2]).intValue(),            // veces_usado
                    (BigDecimal) row[3]                      // total_gastado
            ));
        }
        return reportes;
    }

    @Override
    public List<HistorialClienteDTO> obtenerHistorialCliente(int idCliente) {
        Query query = entityManager.createNativeQuery("CALL reporte_historial_cliente(:idCliente)");
        query.setParameter("idCliente", idCliente);

        List<Object[]> results = query.getResultList();
        List<HistorialClienteDTO> reportes = new ArrayList<>();

        for (Object[] row : results) {
            reportes.add(new HistorialClienteDTO(
                    row[0] != null ? (String) row[0] : null,                            // tipo
                    row[1] != null ? row[1].toString() : null,                         // fecha (Date → String)
                    row[2] != null ? (String) row[2] : null,                           // descripcion
                    row[3] != null ? (String) row[3] : null,                           // mecanico
                    row[4] != null ? ((Number) row[4]).doubleValue() : null           // monto
            ));
        }
        return reportes;
    }

}
