package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.Categoria;

import java.util.List;

public interface CategoriaService {
    Categoria crear(Categoria categoria);
    List<Categoria> listar();
}
