package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "servicio_oficial")
public class ServicioOficial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;

    private String nombre;

    @Column(name = "domicilio")
    private String direccion;

    private String nit;

    @ManyToOne
    @JoinColumn(name = "id_concesionario")
    private Concesionario concesionario;
}
