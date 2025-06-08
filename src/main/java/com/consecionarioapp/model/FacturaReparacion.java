package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "factura_reparacion")
public class FacturaReparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private Integer idFactura;

    @OneToOne
    @JoinColumn(name = "id_reparacion")
    private Reparacion reparacion;

    private BigDecimal total;

    private BigDecimal iva;

    @Column(name = "precio_final")
    private BigDecimal precioFinal;
}
