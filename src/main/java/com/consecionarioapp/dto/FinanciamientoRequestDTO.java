package com.consecionarioapp.dto;

import java.math.BigDecimal;

public class FinanciamientoRequestDTO {

    // 🔹 Datos básicos del financiamiento
    private Integer idFinanciamiento;
    private String tipoFinanciamiento;
    private String entidadFinanciera;
    private BigDecimal monto;
    private Integer cuotas;
    private BigDecimal tasaInteres;
    private Integer plazo;
    private Integer idVenta;

    // 🔹 Información del cliente (solo para mostrar en listados)
    private String clienteNombre;
    private String clienteApellido;

    // 🔹 Constructor vacío
    public FinanciamientoRequestDTO() {
    }

    // 🔹 Getters y Setters
    public Integer getIdFinanciamiento() {
        return idFinanciamiento;
    }

    public void setIdFinanciamiento(Integer idFinanciamiento) {
        this.idFinanciamiento = idFinanciamiento;
    }

    public String getTipoFinanciamiento() {
        return tipoFinanciamiento;
    }

    public void setTipoFinanciamiento(String tipoFinanciamiento) {
        this.tipoFinanciamiento = tipoFinanciamiento;
    }

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getCuotas() {
        return cuotas;
    }

    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }

    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(BigDecimal tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public Integer getPlazo() {
        return plazo;
    }

    public void setPlazo(Integer plazo) {
        this.plazo = plazo;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteApellido() {
        return clienteApellido;
    }

    public void setClienteApellido(String clienteApellido) {
        this.clienteApellido = clienteApellido;
    }
}
