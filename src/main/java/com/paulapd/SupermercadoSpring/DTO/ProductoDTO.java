package com.paulapd.SupermercadoSpring.DTO;

import com.paulapd.SupermercadoSpring.entidades.Categoria;

import java.util.Set;

public record ProductoDTO(
    Long idProducto,
    String nombre,
    String descripcion,
    int stock,
    String marca,
    Categoria categoria_id,
    Set<VentaDTO> ventaDTOSet
    ) {
}
