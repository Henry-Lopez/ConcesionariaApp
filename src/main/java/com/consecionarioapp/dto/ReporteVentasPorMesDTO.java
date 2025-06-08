package com.consecionarioapp.dto;

/**
 * DTO para representar el reporte de ventas por mes.
 * Incluye nombre del modelo, cantidad vendida y total generado en ingresos.
 */
public class ReporteVentasPorMesDTO {

    private String nombreModelo;
    private int cantidadVendida;
    private double ingresoTotal;

    // Constructor vacío requerido por frameworks como Jackson
    public ReporteVentasPorMesDTO() {
    }

    // Constructor completo
    public ReporteVentasPorMesDTO(String nombreModelo, int cantidadVendida, double ingresoTotal) {
        this.nombreModelo = nombreModelo;
        this.cantidadVendida = cantidadVendida;
        this.ingresoTotal = ingresoTotal;
    }

    // Getters y Setters
    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public double getIngresoTotal() {
        return ingresoTotal;
    }

    public void setIngresoTotal(double ingresoTotal) {
        this.ingresoTotal = ingresoTotal;
    }

    @Override
    public String toString() {
        return "ReporteVentasPorMesDTO{" +
                "nombreModelo='" + nombreModelo + '\'' +
                ", cantidadVendida=" + cantidadVendida +
                ", ingresoTotal=" + ingresoTotal +
                '}';
    }
}
