package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.Producto;
import org.hibernate.query.Page;

import java.awt.print.Pageable;

public interface ProductoService {
    Page<Producto> buscar(String nombre, Long categoriaId, Pageable pageable);
    Producto crear(Producto producto);
}
