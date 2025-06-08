// src/main/java/com/consecionarioapp/dto/ManoObraDTO.java
package com.consecionarioapp.dto;

public class ManoObraDTO {

    private Integer idMecanico;
    private Double horasTrabajadas;
    private Double costoHora;

    public ManoObraDTO() {}

    public Integer getIdMecanico() {
        return idMecanico;
    }

    public void setIdMecanico(Integer idMecanico) {
        this.idMecanico = idMecanico;
    }

    public Double getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Double horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public Double getCostoHora() {
        return costoHora;
    }

    public void setCostoHora(Double costoHora) {
        this.costoHora = costoHora;
    }
}
