package com.paulapd.SupermercadoSpring.DTO;

import com.paulapd.SupermercadoSpring.entidades.Categoria;

import java.util.Set;

public record ProductoDTO(
    Long idProducto,
    String nombre,
    String descripcion,
    double precio,
    int stock,
    String marca,
    Long categoria_id
    ) {
}
