package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.ModeloRequestDTO;
import com.consecionarioapp.service.ModeloService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeloServiceImpl implements ModeloService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarModelo(ModeloRequestDTO dto) {

        // 1. CSV limpio
        String extrasCsv = dto.getIdEquiposExtra() == null || dto.getIdEquiposExtra().isEmpty()
                ? null                                             // 👈  manda NULL
                : dto.getIdEquiposExtra().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        String serieCsv  = dto.getIdEquiposSerie() == null || dto.getIdEquiposSerie().isEmpty()
                ? null
                : dto.getIdEquiposSerie().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        StoredProcedureQuery q = entityManager.createStoredProcedureQuery("registrar_modelo")
                .registerStoredProcedureParameter("p_id_marca",             Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre_modelo",        String.class,  ParameterMode.IN)
                .registerStoredProcedureParameter("p_potencia_fiscal",      String.class,  ParameterMode.IN)
                .registerStoredProcedureParameter("p_cilindrada",           Double.class,  ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_puertas",          Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_ruedas",           Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_capacidad_pasajeros",  Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio_base",          BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_descuento",            BigDecimal.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_extras",               String.class,  ParameterMode.IN)
                .registerStoredProcedureParameter("p_serie",                String.class,  ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado",            String.class,  ParameterMode.OUT);

        q.setParameter("p_id_marca",            dto.getIdMarca());
        q.setParameter("p_nombre_modelo",       dto.getNombreModelo());
        q.setParameter("p_potencia_fiscal",     dto.getPotenciaFiscal());
        q.setParameter("p_cilindrada",          Double.parseDouble(dto.getCilindrada()));
        q.setParameter("p_nro_puertas",         dto.getNroPuertas());
        q.setParameter("p_nro_ruedas",          dto.getNroRuedas());
        q.setParameter("p_capacidad_pasajeros", dto.getCapacidadPasajeros());
        q.setParameter("p_precio_base",         dto.getPrecioBase());
        q.setParameter("p_descuento",           dto.getDescuento());
        q.setParameter("p_extras",              extrasCsv);   // ahora puede ser NULL o "1,2,3"
        q.setParameter("p_serie",               serieCsv);

        q.execute();
        return (String) q.getOutputParameterValue("p_resultado");
    }


    @Override
    @Transactional
    public List<ModeloRequestDTO> listarModelos() {
        List<Object[]> results = entityManager.createNativeQuery("CALL listar_modelos()").getResultList();
        List<ModeloRequestDTO> lista = new ArrayList<>();

        for (Object[] row : results) {
            ModeloRequestDTO dto = new ModeloRequestDTO();
            dto.setIdModelo((Integer) row[0]);
            dto.setIdMarca((Integer) row[1]);
            dto.setNombreModelo((String) row[2]);
            dto.setPotenciaFiscal((String) row[3]);
            dto.setCilindrada((String) row[4]);
            dto.setNroPuertas((Integer) row[5]);
            dto.setNroRuedas((Integer) row[6]);
            dto.setCapacidadPasajeros((Integer) row[7]);
            dto.setPrecioBase((BigDecimal) row[8]);
            dto.setDescuento((BigDecimal) row[9]);

            List<Integer> extras = ((List<?>) entityManager.createNativeQuery("SELECT id_equipo FROM modelo_equipo_extra WHERE id_modelo = ?")
                    .setParameter(1, dto.getIdModelo())
                    .getResultList()).stream()
                    .map(o -> ((Number) o).intValue())
                    .collect(Collectors.toList());
            dto.setIdEquiposExtra(extras);

            List<Integer> series = ((List<?>) entityManager.createNativeQuery("SELECT id_equipo FROM modelo_equipo_serie WHERE id_modelo = ?")
                    .setParameter(1, dto.getIdModelo())
                    .getResultList()).stream()
                    .map(o -> ((Number) o).intValue())
                    .collect(Collectors.toList());
            dto.setIdEquiposSerie(series);

            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public String actualizarModelo(int idModelo, ModeloRequestDTO dto) {

        /* ───── 1. Construimos los CSV de equipos ───── */
        String extrasCsv = (dto.getIdEquiposExtra() != null && !dto.getIdEquiposExtra().isEmpty())
                ? dto.getIdEquiposExtra().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","))
                : "";

        String serieCsv  = (dto.getIdEquiposSerie() != null && !dto.getIdEquiposSerie().isEmpty())
                ? dto.getIdEquiposSerie().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","))
                : "";

        /* ───── 2. Llamamos al nuevo SP que ya gestiona las tablas auxiliares ───── */
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_modelo")   // <─ NOMBRE REAL DEL SP
                .registerStoredProcedureParameter("p_id_modelo",           Integer.class,   ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_marca",            Integer.class,   ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre_modelo",       String.class,    ParameterMode.IN)
                .registerStoredProcedureParameter("p_potencia_fiscal",     String.class,    ParameterMode.IN)
                .registerStoredProcedureParameter("p_cilindrada",          Double.class,    ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_puertas",         Integer.class,   ParameterMode.IN)
                .registerStoredProcedureParameter("p_nro_ruedas",          Integer.class,   ParameterMode.IN)
                .registerStoredProcedureParameter("p_capacidad_pasajeros", Integer.class,   ParameterMode.IN)
                .registerStoredProcedureParameter("p_precio_base",         BigDecimal.class,ParameterMode.IN)
                .registerStoredProcedureParameter("p_descuento",           BigDecimal.class,ParameterMode.IN)
                .registerStoredProcedureParameter("p_extras",              String.class,    ParameterMode.IN)
                .registerStoredProcedureParameter("p_serie",               String.class,    ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado",           String.class,    ParameterMode.OUT);

        query.setParameter("p_id_modelo",           idModelo);
        query.setParameter("p_id_marca",            dto.getIdMarca());
        query.setParameter("p_nombre_modelo",       dto.getNombreModelo());
        query.setParameter("p_potencia_fiscal",     dto.getPotenciaFiscal());
        query.setParameter("p_cilindrada",          Double.parseDouble(dto.getCilindrada()));
        query.setParameter("p_nro_puertas",         dto.getNroPuertas());
        query.setParameter("p_nro_ruedas",          dto.getNroRuedas());
        query.setParameter("p_capacidad_pasajeros", dto.getCapacidadPasajeros());
        query.setParameter("p_precio_base",         dto.getPrecioBase());
        query.setParameter("p_descuento",           dto.getDescuento());
        query.setParameter("p_extras",              extrasCsv);
        query.setParameter("p_serie",               serieCsv);

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }


    @Override
    @Transactional
    public String eliminarModelo(int idModelo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_modelo")
                .registerStoredProcedureParameter("p_id_modelo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_modelo", idModelo);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public ModeloRequestDTO obtenerModeloPorId(int idModelo) {
        Object[] row = (Object[]) entityManager.createNativeQuery("SELECT * FROM modelo WHERE id_modelo = ?")
                .setParameter(1, idModelo)
                .getSingleResult();

        ModeloRequestDTO dto = new ModeloRequestDTO();
        dto.setIdModelo((Integer) row[0]);
        dto.setIdMarca((Integer) row[1]);
        dto.setNombreModelo((String) row[2]);
        dto.setPotenciaFiscal((String) row[3]);
        dto.setCilindrada((String) row[4]);
        dto.setNroPuertas((Integer) row[5]);
        dto.setNroRuedas((Integer) row[6]);
        dto.setCapacidadPasajeros((Integer) row[7]);
        dto.setPrecioBase((BigDecimal) row[8]);
        dto.setDescuento((BigDecimal) row[9]);

        List<Integer> extras = ((List<?>) entityManager.createNativeQuery("SELECT id_equipo FROM modelo_equipo_extra WHERE id_modelo = ?")
                .setParameter(1, idModelo)
                .getResultList()).stream()
                .map(o -> ((Number) o).intValue())
                .collect(Collectors.toList());
        dto.setIdEquiposExtra(extras);

        List<Integer> series = ((List<?>) entityManager.createNativeQuery("SELECT id_equipo FROM modelo_equipo_serie WHERE id_modelo = ?")
                .setParameter(1, idModelo)
                .getResultList()).stream()
                .map(o -> ((Number) o).intValue())
                .collect(Collectors.toList());
        dto.setIdEquiposSerie(series);

        return dto;
    }
}
