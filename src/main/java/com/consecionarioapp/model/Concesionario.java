package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "concesionario")
public class Concesionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concesionario")
    private Integer idConcesionario;

    private String nombre;

    @Column(name = "domicilio")
    private String direccion;

    private String nit;
}
