package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.Producto;
import com.paulapd.SupermercadoSpring.repositorio.CategoriaRepositorio;
import com.paulapd.SupermercadoSpring.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepositorio productoRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;

    public ProductoServiceImpl(ProductoRepositorio productoRepositorio, CategoriaRepositorio categoriaRepositorio){
        this.productoRepositorio= productoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;

    }

    @Override
    public Producto crear(Producto producto){
        if(producto.getCategoria()==null){
            throw new RuntimeException("El producto debe tener categoría");
        }
        return productoRepositorio.save(producto);
    }

    @Override
        //Actualizar los datos de un producto
    public Producto actualizar(Producto producto){
        if(!existeProducto(producto.getIdProducto())){
            throw new RuntimeException("El producto no existe");
        }
        return productoRepositorio.save(producto);
        }

    @Override
    //Eliminar un producto de la base de datos.
    public void eliminar(Long id){
        productoRepositorio.deleteById(id);
    }

    @Override
    //Busca un producto por su código o ID.
    public Producto buscarPorId(Long id){
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    @Override
    public Page<Producto> buscar(String nombre, Long categoriaId, Pageable pageable){
        return productoRepositorio
                .findByNombreContainingAndCategoria_IdCategoria(
                        nombre == null?"":nombre,
                        categoriaId,
                        pageable
                );
    }

    @Override
        //Verificar si un producto existe en la base de datos.
    public boolean existeProducto(Long id){
        return productoRepositorio.existsById(id);
    }
}
