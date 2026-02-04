package com.paulapd.SupermercadoSpring.dto;

public record DetalleVentaDTO (
        Long idDetalle,
        int cantidad,
        double precioUnitario,
        Long idProducto
){
}
