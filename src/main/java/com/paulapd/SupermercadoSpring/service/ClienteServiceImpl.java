package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.dto.ClienteDTO;
import com.paulapd.SupermercadoSpring.entidades.Cliente;
import com.paulapd.SupermercadoSpring.repositorio.ClienteRepositorio;
import com.paulapd.SupermercadoSpring.mapper.ClienteMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepositorio clienteRepositorio;

    public ClienteServiceImpl(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    //Crear o actualizar cliente
    public ClienteDTO guardarCliente(Cliente cliente) {
        validarCliente(cliente);
        Cliente clienteGuardado = clienteRepositorio.save(cliente);
        return ClienteMapper.toDTO(clienteGuardado);
    }

    //Obtener todos los clientes
    public List<ClienteDTO> obtenerTodos() {
        return clienteRepositorio.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Buscra por DNI
    public ClienteDTO buscarPorDNI(String dni) {
        if (!validarDNI(dni)) {
            throw new RuntimeException("DNI inválido");
        }
        Cliente cliente = clienteRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return ClienteMapper.toDTO(cliente);
    }

    //Eliminamos cliente
    public void eliminarCliente(String dni) {
        Cliente cliente = clienteRepositorio.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        clienteRepositorio.delete(cliente);
    }

    //ValidarCliente
    private void validarCliente(Cliente cliente) {
        if (!validarDNI(cliente.getDni())) {
            throw new RuntimeException(" DNI inválido. Debe tener 8 dígitos y 1 letra.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            throw new RuntimeException("El apellido es obligatorio.");
        }
        if (!validarTelefono(cliente.getTelefono())) {
            throw new RuntimeException("Teléfono inválido. Debe tener 9 dígitos.");
        }
    }


    //Valiar DNI
    public Boolean validarDNI(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return false;
        }
        //Formato básidco: 8números +1 letra
        String formatDNI = "^[0-9]{8}[A-Za-z]$";
        return dni.matches(formatDNI);
    }

    //Validar Teléfono
    public boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return true; //El telefono es opcional
        }
        //Formato:9dígios
        String formatTel = "^[0-9]{9}$";
        return telefono.matches(formatTel);
    }
}
