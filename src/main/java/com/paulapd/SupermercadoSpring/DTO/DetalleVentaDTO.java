package com.paulapd.SupermercadoSpring.DTO;

import com.paulapd.SupermercadoSpring.entidades.Producto;
import com.paulapd.SupermercadoSpring.entidades.Venta;

public record DetalleVentaDTO (
        Long idDetalle,
        int cantidad,
        double precioUnitario,
        double subtotal,
        Long idProducto
){
}
