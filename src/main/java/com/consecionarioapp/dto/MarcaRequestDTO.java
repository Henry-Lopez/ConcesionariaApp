package com.consecionarioapp.dto;

public class MarcaRequestDTO {
    private Integer idMarca;
    private String nombre;

    public MarcaRequestDTO() {}

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
