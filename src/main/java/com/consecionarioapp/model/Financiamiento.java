package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "financiamiento")
public class Financiamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_financiamiento")
    private Integer idFinanciamiento;

    @Column(name = "tipo_financiamiento")
    private String tipoFinanciamiento;

    @Column(name = "entidad_financiera")
    private String entidadFinanciera;

    private BigDecimal monto;

    private Integer cuotas;

    @Column(name = "tasa_interes")
    private BigDecimal tasaInteres;

    private Integer plazo;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
}
