package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    private String nombre;
    private String apellido;

    private String direccion;
    private String telefono;
    private String correo;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    private String cargo;

    @Column(name = "trabaja_en")
    private String trabajaEn; // También puedes usar Enum si lo prefieres

    @ManyToOne
    @JoinColumn(name = "id_concesionario")
    private Concesionario concesionario;

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private ServicioOficial servicioOficial;
}
