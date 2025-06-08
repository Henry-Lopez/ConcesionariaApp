package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca")
    private Integer idMarca;

    @Column(nullable = false)
    private String nombre;
}
