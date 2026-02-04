package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    DetalleVenta insertarDetalle(DetalleVenta detalleVenta);
    List<DetalleVenta> obtenerPorVenta(Long idVenta);
    void eliminarPorVenta(Long idVenta);
    DetalleVenta buscarPorId(Long id);
    double calcularTotalVenta(Long idVenta);

}
