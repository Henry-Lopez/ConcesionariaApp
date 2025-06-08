package com.consecionarioapp.service.serviceImpl;

import com.consecionarioapp.dto.ClienteRequestDTO;
import com.consecionarioapp.service.ClienteService;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public String registrarCliente(ClienteRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("registrar_cliente")
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_nacimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_apellido", dto.getApellido());
        query.setParameter("p_direccion", dto.getDireccion());
        query.setParameter("p_telefono", dto.getTelefono());
        query.setParameter("p_correo", dto.getCorreo());
        query.setParameter("p_fecha_nacimiento", Date.valueOf(dto.getFechaNacimiento()));

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public List<ClienteRequestDTO> listarClientes() {
        List<ClienteRequestDTO> clientes = new ArrayList<>();
        try {
            List<Object[]> results = entityManager.createNativeQuery("CALL listar_clientes()").getResultList();

            for (Object[] row : results) {
                ClienteRequestDTO dto = new ClienteRequestDTO();
                dto.setIdCliente((Integer) row[0]);
                dto.setNombre((String) row[1]);
                dto.setApellido((String) row[2]);
                dto.setDireccion((String) row[3]);
                dto.setTelefono((String) row[4]);
                dto.setCorreo((String) row[5]);
                dto.setFechaNacimiento(row[6] != null ? ((Date) row[6]).toLocalDate() : null);
                clientes.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }


    @Override
    @Transactional
    public String actualizarCliente(ClienteRequestDTO dto) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("actualizar_cliente")
                .registerStoredProcedureParameter("p_id_cliente", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_nacimiento", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_cliente", dto.getIdCliente());
        query.setParameter("p_nombre", dto.getNombre());
        query.setParameter("p_apellido", dto.getApellido());
        query.setParameter("p_direccion", dto.getDireccion());
        query.setParameter("p_telefono", dto.getTelefono());
        query.setParameter("p_correo", dto.getCorreo());
        query.setParameter("p_fecha_nacimiento", Date.valueOf(dto.getFechaNacimiento()));

        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public String eliminarCliente(int idCliente) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("eliminar_cliente")
                .registerStoredProcedureParameter("p_id_cliente", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_resultado", String.class, ParameterMode.OUT);

        query.setParameter("p_id_cliente", idCliente);
        query.execute();
        return (String) query.getOutputParameterValue("p_resultado");
    }

    @Override
    @Transactional
    public ClienteRequestDTO obtenerClientePorId(int idCliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, idCliente);

        List<Object[]> result = query.getResultList();

        if (result.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado");
        }

        Object[] row = result.get(0);
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setIdCliente((Integer) row[0]);
        dto.setNombre((String) row[1]);
        dto.setApellido((String) row[2]);
        dto.setDireccion((String) row[3]);
        dto.setTelefono((String) row[4]);
        dto.setCorreo((String) row[5]);
        dto.setFechaNacimiento(row[6] != null ? ((Date) row[6]).toLocalDate() : null);

        return dto;
    }
}
