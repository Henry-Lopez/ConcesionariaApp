package com.consecionarioapp.dto;

public class ReporteReparacionesPorMecanicoDTO {
    private String nombre;
    private String apellido;
    private int cantidadReparaciones;
    private double totalGenerado;

    public ReporteReparacionesPorMecanicoDTO() {
    }

    public ReporteReparacionesPorMecanicoDTO(String nombre, String apellido, int cantidadReparaciones, double totalGenerado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidadReparaciones = cantidadReparaciones;
        this.totalGenerado = totalGenerado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCantidadReparaciones() {
        return cantidadReparaciones;
    }

    public void setCantidadReparaciones(int cantidadReparaciones) {
        this.cantidadReparaciones = cantidadReparaciones;
    }

    public double getTotalGenerado() {
        return totalGenerado;
    }

    public void setTotalGenerado(double totalGenerado) {
        this.totalGenerado = totalGenerado;
    }
}
