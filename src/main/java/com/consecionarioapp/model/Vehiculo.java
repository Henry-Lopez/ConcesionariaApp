package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @Column(name = "nro_chasis")
    private String nroChasis;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo;

    private String color;

    @Column(name = "año")
    private Integer anio;

    private BigDecimal precio;

    private String estado;

    @Column(name = "en_stock")
    private boolean enStock;

    @Column(name = "ubicacion")
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_concesionario")
    private Concesionario concesionario;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private ServicioOficial servicio;
}
