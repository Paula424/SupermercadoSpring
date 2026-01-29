package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.DTO.ClienteDTO;
import com.paulapd.SupermercadoSpring.entidades.Cliente;
import com.paulapd.SupermercadoSpring.repositorio.ClienteRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService{
private final ClienteRepositorio clienteRepositorio;

public ClienteServiceImpl(ClienteRepositorio clienteRepositorio){
    this.clienteRepositorio = clienteRepositorio;
}

//Los clientes ordenados por apellidos
    public static List<Cliente> obtenerPorApell() {
    }

//Valiar DNI
    public Boolean validarDNI(String dni){
    if (dni == null || dni.trim().isEmpty()){
        return false;
    }
    //Formato básidco: 8números +1 letra
        String formatDNI = "^[0-9]{8}[A-Za-z]$";
    return dni.matches(formatDNI);
    }

    //Validar Teléfono
    public boolean validarTelefono(String telefono) {
    if(telefono ==  null || telefono.trim().isEmpty()){
        return true; //El telefono es opcional
    }
    //Formato:9dígios
        String formatTel = "^[0-9]{9}$";
    return telefono.matches(formatTel);
    }

    //Inserta un nuevo cliente tras validar los datos
    public boolean insertarCliente(Cliente cliente){
    //Validar DNI
        if(!validarDNI(cliente.getDni())){
            System.err.println("ERROR: DNI inválido. Debe tener 8 dígitos y 1 letra.");
            return false;
        }

        //Validar que el nombre y apelidos NO estén vacíos
        if(cliente.getNombre()== null|| cliente.getNombre().trim().isEmpty()){
            System.err.println("ERROR: El nombre es obligatorio.");
            return false;
        }

        if(cliente.getApellido()==null||cliente.getApellido().trim().isEmpty()){
            System.err.println("ERROR:Los apellidos con obligatorios");
            return false;
        }

        //VAlidar telefono si se proporciona.
        if (!validarTelefono(cliente.getTelefono())){
            System.err.println("ERROR: Teléfono invalido. Debe tener 9 dígitos");
            return false;
        }

        //Si está todo correcto insertar el cliente.
        return clienteService.insertarCliente(cliente);
    }

    //Buscra por DNI
    public Cliente buscarPorDNI(String dni){
    if(!validarDNI(dni)){
        System.err.println("ERROR:DNI inválido");
        return null;
    }
    return clienteService.buscarPorDNI(dni);
    }

    //Actualizar los datos de un cliente
    public boolean actualizarCliente(Cliente cliente){
    //Las mismas validaciones que insertar.
        //Validar DNI
        if(!validarDNI(cliente.getDni())){
            System.err.println("ERROR: DNI inválido. Debe tener 8 dígitos y 1 letra.");
            return false;
        }

        //Validar que el nombre y apelidos NO estén vacíos
        if(cliente.getNombre()== null|| cliente.getNombre().trim().isEmpty()){
            System.err.println("ERROR: El nombre es obligatorio.");
            return false;
        }

        if(cliente.getApellido()==null||cliente.getApellido().trim().isEmpty()){
            System.err.println("ERROR:Los apellidos con obligatorios");
            return false;
        }

        //VAlidar telefono si se proporciona.
        if (!validarTelefono(cliente.getTelefono())){
            System.err.println("ERROR: Teléfono invalido. Debe tener 9 dígitos");
            return false;
        }
        return clientesService.actualizarCliente(cliente);
    }

//Eliminamos cliente
    public boolean eliminarCliente(String dni){
    if (validarDNI(dni)){
        System.err.println("ERROR: DNI inválido");
        return false;
    }
    return clienteService.eliminarCliente(dni);
    }

    //Virificar si eun clienete existe en la base de datos.
    public


}
