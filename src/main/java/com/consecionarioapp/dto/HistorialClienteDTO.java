package com.consecionarioapp.dto;

public class HistorialClienteDTO {
    private String tipo;         // "Venta" o "Reparación", puede ser null
    private String fecha;        // Puede ser null
    private String descripcion;  // Puede ser null
    private String mecanico;     // Puede ser null
    private Double monto;        // Puede ser null

    public HistorialClienteDTO() {
    }

    public HistorialClienteDTO(String tipo, String fecha, String descripcion, String mecanico, Double monto) {
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.mecanico = mecanico;
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMecanico() {
        return mecanico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
