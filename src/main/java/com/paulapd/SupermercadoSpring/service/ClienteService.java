package com.paulapd.SupermercadoSpring.service;

public interface ClienteService {
    public boolean insertarCliente(cliente) {}
    public boolean buscarPorDNI(dni){}
    public boolean actualizarCliente(cliente){}
    public boolean eliminarCliente(dni){}
    public boolean existeCliente(String dni) {
        return buscarPorDni(dni) != null;
    }



}
