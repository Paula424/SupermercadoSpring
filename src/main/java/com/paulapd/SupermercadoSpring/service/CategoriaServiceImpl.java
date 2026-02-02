package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.Categoria;
import com.paulapd.SupermercadoSpring.repositorio.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    private final CategoriaRepositorio categoriaRepositorio;

    public CategoriaServiceImpl(CategoriaRepositorio categoriaRepositorio){
        this.categoriaRepositorio=categoriaRepositorio;
    }

    @Override
    public Categoria crear(Categoria categoria){
        return categoriaRepositorio.save(categoria);
    }

    @Override
    public List<Categoria> listar(){
        return categoriaRepositorio.findAll();
    }

}
