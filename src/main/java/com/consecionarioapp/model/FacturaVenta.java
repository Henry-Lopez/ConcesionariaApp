package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "factura_venta")
public class FacturaVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFactura;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    private BigDecimal total;

    private BigDecimal iva;

    @Column(name = "precio_final")
    private BigDecimal precioFinal;
}
