package com.paulapd.SupermercadoSpring.repositorio;

import com.paulapd.SupermercadoSpring.entidades.Producto;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Long> {

    Page<Producto> finByNombreContainingAndCategoria_IdCategoria(
            String nombre,
            Long id_categoria,
            Pageable pageable
    );
}
