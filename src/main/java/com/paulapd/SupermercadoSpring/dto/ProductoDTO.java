package com.paulapd.SupermercadoSpring.dto;

public record ProductoDTO(
    Long idProducto,
    String nombre,
    String descripcion,
    double precio,
    int stock,
    String marca,
    Long categoriaId
    ) {
}
