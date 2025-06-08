package com.consecionarioapp.dto;

import java.math.BigDecimal;

public class EquipoRequestDTO {
    private Integer idEquipo;
    private String descripcion;
    private BigDecimal precio;

    public EquipoRequestDTO() {}

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
