package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    private String nombre;

    private String apellido;

    private String direccion;

    private String telefono;

    private String correo;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
}
