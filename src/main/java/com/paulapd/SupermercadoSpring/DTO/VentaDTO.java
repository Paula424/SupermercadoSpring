package com.paulapd.SupermercadoSpring.DTO;

import com.paulapd.SupermercadoSpring.entidades.Cliente;
import com.paulapd.SupermercadoSpring.entidades.Producto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record VentaDTO(
        Long idVenta,
        LocalDateTime fecha,
        double total,
        String metodoPago,
        List<DetalleVentaDTO> detalles,
        ClienteDTO cliente_id
) {
}
