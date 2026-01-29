package com.paulapd.SupermercadoSpring.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record VentaResponseDTO(
        Long idVenta,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin,
        double total,
        String metodoPago,
        List<DetalleVentaDTO> detalles,
        ClienteDTO cliente
) {
}
