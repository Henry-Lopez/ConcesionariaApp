package com.consecionarioapp.dto;

/**
 * DTO que representa el reporte de ventas agrupadas por empleado.
 * Incluye nombre, apellido, cantidad total de ventas realizadas y monto total generado.
 */
public class ReporteVentasPorEmpleadoDTO {

    private String nombre;
    private String apellido;
    private int cantidadVentas;
    private double totalGenerado;

    public ReporteVentasPorEmpleadoDTO() {
    }

    public ReporteVentasPorEmpleadoDTO(String nombre, String apellido, int cantidadVentas, double totalGenerado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidadVentas = cantidadVentas;
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

    public int getCantidadVentas() {
        return cantidadVentas;
    }

    public void setCantidadVentas(int cantidadVentas) {
        this.cantidadVentas = cantidadVentas;
    }

    public double getTotalGenerado() {
        return totalGenerado;
    }

    public void setTotalGenerado(double totalGenerado) {
        this.totalGenerado = totalGenerado;
    }

    @Override
    public String toString() {
        return "ReporteVentasPorEmpleadoDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", cantidadVentas=" + cantidadVentas +
                ", totalGenerado=" + totalGenerado +
                '}';
    }
}
