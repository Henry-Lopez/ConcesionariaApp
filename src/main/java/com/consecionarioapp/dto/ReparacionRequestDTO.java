package com.consecionarioapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ReparacionRequestDTO {

    private Integer idReparacion; // útil para editar
    private LocalDate fechaEntrada;
    private LocalTime horaEntrada;
    private String descripcion;
    private Integer idCliente;
    private String nroChasis;

    private String diagnostico; // ✅ Nuevo campo
    private String garantia;    // ✅ Nuevo campo

    private List<Integer> idMecanicos; // mecánicos auxiliares
    private List<Integer> idRepuestos;

    // ✅ JSON recibido del frontend (opcional)
    private String manoObraJson;

    // ✅ Lista estructurada para backend
    private List<ManoObraDTO> manoObra;

    public ReparacionRequestDTO() {}

    /* ───── Getters y Setters ───── */

    public Integer getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNroChasis() {
        return nroChasis;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public List<Integer> getIdMecanicos() {
        return idMecanicos;
    }

    public void setIdMecanicos(List<Integer> idMecanicos) {
        this.idMecanicos = idMecanicos;
    }

    public List<Integer> getIdRepuestos() {
        return idRepuestos;
    }

    public void setIdRepuestos(List<Integer> idRepuestos) {
        this.idRepuestos = idRepuestos;
    }

    public String getManoObraJson() {
        return manoObraJson;
    }

    public void setManoObraJson(String manoObraJson) {
        this.manoObraJson = manoObraJson;
    }

    public List<ManoObraDTO> getManoObra() {
        return manoObra;
    }

    public void setManoObra(List<ManoObraDTO> manoObra) {
        this.manoObra = manoObra;
    }
}
