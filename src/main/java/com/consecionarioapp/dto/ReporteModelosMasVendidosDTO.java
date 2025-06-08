package com.consecionarioapp.dto;

import java.math.BigDecimal;

public class ReporteModelosMasVendidosDTO {
    private String nombreModelo;
    private Long unidadesVendidas;
    private BigDecimal totalGenerado;

    public ReporteModelosMasVendidosDTO() {
    }

    public ReporteModelosMasVendidosDTO(String nombreModelo, Long unidadesVendidas, BigDecimal totalGenerado) {
        this.nombreModelo = nombreModelo;
        this.unidadesVendidas = unidadesVendidas;
        this.totalGenerado = totalGenerado;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Long getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(Long unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }

    public BigDecimal getTotalGenerado() {
        return totalGenerado;
    }

    public void setTotalGenerado(BigDecimal totalGenerado) {
        this.totalGenerado = totalGenerado;
    }
}
