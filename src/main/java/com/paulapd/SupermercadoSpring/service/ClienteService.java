package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.dto.ClienteDTO;
import com.paulapd.SupermercadoSpring.entidades.Cliente;

import java.util.List;

public interface ClienteService {
   //Crear o actualizar cliente
    ClienteDTO guardarCliente(Cliente cliente);

    //Obtener todos los clientes
    List<ClienteDTO> obtenerTodos();

    ClienteDTO buscarPorDNI(String dni);

    void eliminarCliente(String dni);


}
