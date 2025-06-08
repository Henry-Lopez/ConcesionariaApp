package com.consecionarioapp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class VentaRequestDTO {

    private Integer idVenta;
    private Integer idCliente;
    private Integer idEmpleado;
    private String nroChasis;
    private String formaPago;
    private Integer cuotas;
    private BigDecimal precioTotal;
    private LocalDate fechaVenta;
    private LocalDate fechaEntrega;
    private String matricula;
    private boolean esStock;
    private String origen;
    private List<Integer> idExtras;   // ✅ lista de equipos extra

    /* ---------- Constructores ---------- */

    // Constructor vacío (Jackson / Lombok, etc.)
    public VentaRequestDTO() { }

    // Constructor completo CON idExtras
    public VentaRequestDTO(Integer idVenta, Integer idCliente, Integer idEmpleado,
                           String nroChasis, String formaPago, Integer cuotas,
                           BigDecimal precioTotal, LocalDate fechaVenta,
                           LocalDate fechaEntrega, String matricula,
                           boolean esStock, String origen,
                           List<Integer> idExtras) {
        this.idVenta       = idVenta;
        this.idCliente     = idCliente;
        this.idEmpleado    = idEmpleado;
        this.nroChasis     = nroChasis;
        this.formaPago     = formaPago;
        this.cuotas        = cuotas;
        this.precioTotal   = precioTotal;
        this.fechaVenta    = fechaVenta;
        this.fechaEntrega  = fechaEntrega;
        this.matricula     = matricula;
        this.esStock       = esStock;
        this.origen        = origen;
        this.idExtras      = idExtras;   // ✅ asignación
    }

    // (Opcional) Constructor sin extras para compatibilidad
    public VentaRequestDTO(Integer idVenta, Integer idCliente, Integer idEmpleado,
                           String nroChasis, String formaPago, Integer cuotas,
                           BigDecimal precioTotal, LocalDate fechaVenta,
                           LocalDate fechaEntrega, String matricula,
                           boolean esStock, String origen) {
        this(idVenta, idCliente, idEmpleado, nroChasis, formaPago, cuotas,
                precioTotal, fechaVenta, fechaEntrega, matricula, esStock, origen, null);
    }

    /* ---------- Getters & Setters ---------- */

    public Integer getIdVenta()             { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }

    public Integer getIdCliente()                 { return idCliente; }
    public void setIdCliente(Integer idCliente)   { this.idCliente = idCliente; }

    public Integer getIdEmpleado()                { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNroChasis()              { return nroChasis; }
    public void setNroChasis(String nroChasis){ this.nroChasis = nroChasis; }

    public String getFormaPago()               { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }

    public Integer getCuotas()             { return cuotas; }
    public void setCuotas(Integer cuotas)  { this.cuotas = cuotas; }

    public BigDecimal getPrecioTotal()                   { return precioTotal; }
    public void setPrecioTotal(BigDecimal precioTotal)   { this.precioTotal = precioTotal; }

    public LocalDate getFechaVenta()               { return fechaVenta; }
    public void setFechaVenta(LocalDate fechaVenta){ this.fechaVenta = fechaVenta; }

    public LocalDate getFechaEntrega()                 { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega){ this.fechaEntrega = fechaEntrega; }

    public String getMatricula()                { return matricula; }
    public void setMatricula(String matricula)  { this.matricula = matricula; }

    public boolean isEsStock()           { return esStock; }
    public void setEsStock(boolean esStock) { this.esStock = esStock; }

    public String getOrigen()             { return origen; }
    public void setOrigen(String origen)  { this.origen = origen; }

    public List<Integer> getIdExtras()                     { return idExtras; }
    public void setIdExtras(List<Integer> idExtras)        { this.idExtras = idExtras; }
}
