package com.consecionarioapp.dto;

import java.math.BigDecimal;

public class ReporteRepuestosMasUsadosDTO {
    private String descripcion;
    private int vecesUtilizado;
    private BigDecimal totalGastado;

    public ReporteRepuestosMasUsadosDTO() {
    }

    public ReporteRepuestosMasUsadosDTO(String descripcion, int vecesUtilizado, BigDecimal totalGastado) {
        this.descripcion = descripcion;
        this.vecesUtilizado = vecesUtilizado;
        this.totalGastado = totalGastado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getVecesUtilizado() {
        return vecesUtilizado;
    }

    public void setVecesUtilizado(int vecesUtilizado) {
        this.vecesUtilizado = vecesUtilizado;
    }

    public BigDecimal getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(BigDecimal totalGastado) {
        this.totalGastado = totalGastado;
    }
}
