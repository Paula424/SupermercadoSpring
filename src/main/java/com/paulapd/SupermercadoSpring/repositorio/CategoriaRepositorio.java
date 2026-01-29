package com.paulapd.SupermercadoSpring.repositorio;

import com.paulapd.SupermercadoSpring.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
}
