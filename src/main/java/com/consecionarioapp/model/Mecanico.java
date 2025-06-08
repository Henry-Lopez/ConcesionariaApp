package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "mecanico")
public class Mecanico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mecanico")
    private Integer idMecanico;

    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String especialidad;

    private BigDecimal salario;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;
}
