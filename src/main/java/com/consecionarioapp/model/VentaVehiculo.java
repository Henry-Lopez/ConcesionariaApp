package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "venta_vehiculo")
public class VentaVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Campo artificial solo para JPA (NO existe en la base real)

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "nro_chasis")
    private Vehiculo vehiculo;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    private String matricula;

    @Column(name = "es_stock")
    private boolean esStock;

    private String origen;
}
