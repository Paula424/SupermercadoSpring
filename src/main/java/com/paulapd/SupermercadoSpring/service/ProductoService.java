package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {
    Producto actualizar(Producto producto);
    void eliminar(Long id);
    Producto buscarPorId(Long id);
    Page<Producto> buscar(String nombre, Long categoriaId, Pageable pageable);
    Producto crear(Producto producto);
    boolean existeProducto(Long id);
}
