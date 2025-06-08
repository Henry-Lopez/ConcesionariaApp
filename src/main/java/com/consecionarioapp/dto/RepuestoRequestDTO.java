package com.consecionarioapp.dto;

import java.math.BigDecimal;

public class RepuestoRequestDTO {

    private Integer idRepuesto; // ✅ Campo agregado
    private String descripcion;
    private BigDecimal precioUnidad;

    public RepuestoRequestDTO() {
    }

    public Integer getIdRepuesto() {
        return idRepuesto;
    }

    public void setIdRepuesto(Integer idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(BigDecimal precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
}
