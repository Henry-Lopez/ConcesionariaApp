package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modelo")
    private Integer idModelo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @Column(name = "nombre_modelo")
    private String nombreModelo;

    @Column(name = "potencia_fiscal")
    private String potenciaFiscal;

    private String cilindrada;

    @Column(name = "nro_puertas")
    private Integer nroPuertas;

    @Column(name = "nro_ruedas")
    private Integer nroRuedas;

    @Column(name = "capacidad_pasajeros")
    private Integer capacidadPasajeros;

    @Column(name = "precio_base")
    private BigDecimal precioBase;

    private BigDecimal descuento;
}
