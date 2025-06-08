// PedidoResumenDTO.java
package com.consecionarioapp.dto;

public class PedidoResumenDTO {
    private String descripcionRepuesto;
    private Integer totalPendientes;
    private Integer totalEntregados;

    public PedidoResumenDTO() {}

    public PedidoResumenDTO(String descripcionRepuesto, Integer totalPendientes, Integer totalEntregados) {
        this.descripcionRepuesto = descripcionRepuesto;
        this.totalPendientes = totalPendientes;
        this.totalEntregados = totalEntregados;
    }

    public String getDescripcionRepuesto() {
        return descripcionRepuesto;
    }

    public void setDescripcionRepuesto(String descripcionRepuesto) {
        this.descripcionRepuesto = descripcionRepuesto;
    }

    public Integer getTotalPendientes() {
        return totalPendientes;
    }

    public void setTotalPendientes(Integer totalPendientes) {
        this.totalPendientes = totalPendientes;
    }

    public Integer getTotalEntregados() {
        return totalEntregados;
    }

    public void setTotalEntregados(Integer totalEntregados) {
        this.totalEntregados = totalEntregados;
    }
}
