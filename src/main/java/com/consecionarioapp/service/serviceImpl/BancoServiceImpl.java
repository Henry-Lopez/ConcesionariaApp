package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.BancoRequestDTO;
import com.consecionarioapp.service.BancoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BancoServiceImpl implements BancoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarBanco(BancoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_banco")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cuenta", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_financiamiento", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_cuenta", dto.getNumeroCuenta());
        query.setParameter("p_id_financiamiento", dto.getIdFinanciamiento());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<BancoRequestDTO> listarBancos() {
        List<Object[]> resultados = entityManager
                .createNativeQuery("CALL listar_bancos()")
                .getResultList();

        List<BancoRequestDTO> lista = new ArrayList<>();
        for (Object[] row : resultados) {
            BancoRequestDTO dto = new BancoRequestDTO();
            dto.setIdBanco((Integer) row[0]);
            dto.setNombre((String) row[1]);
            dto.setNumeroCuenta((String) row[2]);
            dto.setIdFinanciamiento((Integer) row[3]);
            lista.add(dto);
        }

        return lista;
    }

    @Override
    @Transactional
    public BancoRequestDTO obtenerBancoPorId(int idBanco) {
        Object[] row = (Object[]) entityManager
                .createNativeQuery("SELECT * FROM banco WHERE id_banco = ?")
                .setParameter(1, idBanco)
                .getSingleResult();

        BancoRequestDTO dto = new BancoRequestDTO();
        dto.setIdBanco((Integer) row[0]);
        dto.setNombre((String) row[1]);
        dto.setNumeroCuenta((String) row[2]);
        dto.setIdFinanciamiento((Integer) row[3]);
        return dto;
    }

    @Override
    @Transactional
    public String actualizarBanco(BancoRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_banco")
                .registerStoredProcedureParameter("p_id_banco", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cuenta", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_financiamiento", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_banco", dto.getIdBanco());
        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_cuenta", dto.getNumeroCuenta());
        query.setParameter("p_id_financiamiento", dto.getIdFinanciamiento());

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarBanco(int idBanco) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_banco")
                .registerStoredProcedureParameter("p_id_banco", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_banco", idBanco);

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }
}
