package com.paulapd.SupermercadoSpring.DTO;


import java.util.List;

public record VentaCreateDTO(
        Long clienteID,
        String metodoPago,
        List<DetalleVentaDTO> detalleVentaDTOS
) {
}
