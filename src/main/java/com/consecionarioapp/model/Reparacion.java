package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "reparacion")
public class Reparacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reparacion")
    private Integer idReparacion;

    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    @Column(name = "hora_entrada")
    private LocalTime horaEntrada;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "nro_chasis") // ✅ Clave foránea a String PK
    private Vehiculo vehiculo;
}
