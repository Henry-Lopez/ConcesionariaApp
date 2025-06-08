package com.consecionarioapp.dto;

import java.math.BigDecimal;

/**
 * DTO para representar los ingresos totales agrupados por mes y tipo de origen.
 * Se utiliza en el reporte de ingresos (ventas y reparaciones).
 */
public class ReporteIngresosTotalesDTO {

    private String origen;     // "Venta" o "Reparación"
    private Integer mes;
    private Integer anio;
    private BigDecimal total;

    public ReporteIngresosTotalesDTO() {
    }

    public ReporteIngresosTotalesDTO(String origen, Integer mes, Integer anio, BigDecimal total) {
        this.origen = origen;
        this.mes = mes;
        this.anio = anio;
        this.total = total;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ReporteIngresosTotalesDTO{" +
                "origen='" + origen + '\'' +
                ", mes=" + mes +
                ", anio=" + anio +
                ", total=" + total +
                '}';
    }
}
