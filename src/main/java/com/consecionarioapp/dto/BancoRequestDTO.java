package com.consecionarioapp.dto;

public class BancoRequestDTO {

    private Integer idBanco;
    private String nombre;
    private String numeroCuenta;
    private Integer idFinanciamiento;

    public BancoRequestDTO() {
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Integer getIdFinanciamiento() {
        return idFinanciamiento;
    }

    public void setIdFinanciamiento(Integer idFinanciamiento) {
        this.idFinanciamiento = idFinanciamiento;
    }
}
