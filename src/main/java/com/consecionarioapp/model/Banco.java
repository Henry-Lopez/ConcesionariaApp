package com.consecionarioapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_banco")
    private Integer idBanco;

    private String nombre;

    @Column(name = "numero_cuenta")
    private String numeroCuenta;

    @ManyToOne
    @JoinColumn(name = "id_financiamiento")
    private Financiamiento financiamiento;
}
