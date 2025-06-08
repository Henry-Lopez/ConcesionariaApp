package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.FacturaReparacionDTO;
import com.consecionarioapp.service.FacturaReparacionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaReparacionServiceImpl implements FacturaReparacionService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<FacturaReparacionDTO> listarFacturasReparacion() {
        List<Object[]> resultados = entityManager.createNativeQuery("CALL listar_facturas_reparacion()").getResultList();
        List<FacturaReparacionDTO> lista = new ArrayList<>();

        for (Object[] row : resultados) {
            FacturaReparacionDTO dto = new FacturaReparacionDTO();
            dto.setIdFactura((Integer) row[0]);
            dto.setIdReparacion((Integer) row[1]);
            dto.setCliente((String) row[2]);
            dto.setTotal(((Number) row[3]).doubleValue());
            dto.setIva(((Number) row[4]).doubleValue());
            dto.setPrecioFinal(((Number) row[5]).doubleValue());
            lista.add(dto);
        }

        return lista;
    }
}
