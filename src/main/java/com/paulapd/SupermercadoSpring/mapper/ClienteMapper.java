package com.paulapd.SupermercadoSpring.mapper;


import com.paulapd.SupermercadoSpring.dto.ClienteDTO;
import com.paulapd.SupermercadoSpring.entidades.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente cliente){
        return new ClienteDTO(
                cliente.getIdCliente(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getDni(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getDireccion(),
                cliente.getFechaRegistro()
        );
    }
}
