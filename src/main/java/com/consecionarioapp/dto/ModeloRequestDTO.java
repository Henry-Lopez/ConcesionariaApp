package com.consecionarioapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;   // ✅ para mapear nombres del JSON
import java.math.BigDecimal;
import java.util.List;

public class ModeloRequestDTO {

    private Integer idModelo;
    private Integer idMarca;
    private String nombreModelo;
    private String potenciaFiscal;
    private String cilindrada;
    private Integer nroPuertas;
    private Integer nroRuedas;
    private Integer capacidadPasajeros;
    private BigDecimal precioBase;
    private BigDecimal descuento;

    /* ----------------------------------------------------------------
       Estos dos campos son los que llegan desde el front como:
       - "equiposExtra": [...]
       - "equiposSerie": [...]
       Con @JsonProperty garantizamos el mapeo automático.
    ---------------------------------------------------------------- */
    @JsonProperty("equiposExtra")
    private List<Integer> idEquiposExtra;

    @JsonProperty("equiposSerie")
    private List<Integer> idEquiposSerie;

    /* -------------------  constructor vacío ------------------- */
    public ModeloRequestDTO() { }

    /* -------------------  getters / setters ------------------- */
    public Integer getIdModelo()               { return idModelo; }
    public void    setIdModelo(Integer id)     { this.idModelo = id; }

    public Integer getIdMarca()                { return idMarca; }
    public void    setIdMarca(Integer idMarca) { this.idMarca = idMarca; }

    public String  getNombreModelo()                 { return nombreModelo; }
    public void    setNombreModelo(String nombre)    { this.nombreModelo = nombre; }

    public String  getPotenciaFiscal()               { return potenciaFiscal; }
    public void    setPotenciaFiscal(String potencia){ this.potenciaFiscal = potencia; }

    public String  getCilindrada()                   { return cilindrada; }
    public void    setCilindrada(String cilindrada)  { this.cilindrada = cilindrada; }

    public Integer getNroPuertas()                   { return nroPuertas; }
    public void    setNroPuertas(Integer nroPuertas) { this.nroPuertas = nroPuertas; }

    public Integer getNroRuedas()                    { return nroRuedas; }
    public void    setNroRuedas(Integer nroRuedas)   { this.nroRuedas = nroRuedas; }

    public Integer getCapacidadPasajeros()                    { return capacidadPasajeros; }
    public void    setCapacidadPasajeros(Integer capacidad)   { this.capacidadPasajeros = capacidad; }

    public BigDecimal getPrecioBase()                       { return precioBase; }
    public void       setPrecioBase(BigDecimal precioBase)   { this.precioBase = precioBase; }

    public BigDecimal getDescuento()                       { return descuento; }
    public void       setDescuento(BigDecimal descuento)   { this.descuento = descuento; }

    /* ------------ listas de equipos --------------- */
    public List<Integer> getIdEquiposExtra()                   { return idEquiposExtra; }
    public void          setIdEquiposExtra(List<Integer> list) { this.idEquiposExtra = list; }

    public List<Integer> getIdEquiposSerie()                   { return idEquiposSerie; }
    public void          setIdEquiposSerie(List<Integer> list) { this.idEquiposSerie = list; }
}
