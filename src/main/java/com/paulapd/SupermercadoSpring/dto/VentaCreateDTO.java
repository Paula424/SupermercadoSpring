package com.paulapd.SupermercadoSpring.dto;


import java.util.List;

public record VentaCreateDTO(
        Long clienteID,
        String metodoPago,
        List<DetalleVentaDTO> detalleVentaDTOS
) {
}
