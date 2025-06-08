package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.FacturaVentaDTO;
import com.consecionarioapp.service.FacturaVentaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaVentaServiceImpl implements FacturaVentaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<FacturaVentaDTO> listarFacturasVenta() {
        List<Object[]> resultados = entityManager
                .createNativeQuery("CALL listar_facturas_venta()")
                .getResultList();

        List<FacturaVentaDTO> lista = new ArrayList<>();

        for (Object[] row : resultados) {
            FacturaVentaDTO dto = new FacturaVentaDTO();
            dto.setIdFactura((Integer) row[0]);
            dto.setIdVenta((Integer) row[1]);
            dto.setCliente((String) row[2]);
            dto.setTotal(((Number) row[3]).doubleValue());
            dto.setIva(((Number) row[4]).doubleValue());
            dto.setPrecioFinal(((Number) row[5]).doubleValue());
            lista.add(dto);
        }

        return lista;
    }
}
