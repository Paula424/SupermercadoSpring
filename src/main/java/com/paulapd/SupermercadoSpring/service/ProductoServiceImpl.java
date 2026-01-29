package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.Producto;
import com.paulapd.SupermercadoSpring.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepositorio productoRepositorio;

    public ProductoServiceImpl(){

    }

    //Validar que el codigo del producto no esté vacio.
    public boolean validarCoddigo(String codProdcuto){
        return codProdcuto != null && !codProdcuto.trim().isEmpty();
    }

    //Valida que el precio sea mayor que 0.
    public boolean validarPrecio(double precio){
        return precio !=null && precio.compareTo(double.ZERO) > 0;
    }

    //Valdia que las existencias sean mayores o iguales a 0.
    public boolean validarExistencias(int existencias){
        return existencias >=0;
    }

    //Inserta un nuevo producto tras validar los datos.
    public boolean inaertarProducto(Producto producto){
        //Validar Código.
        if(!validarCoddigo(producto.getIdProducto())){
            System.err.println("ERROR: El código o el ID del producto es obligatorio");
            return false;
        }

        //Validar descripción
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()){
            System.err.println("ERROR: La descirpción del producto ess obligatoria.");
            return false;
        }

        //Validar precio.
        if (!validarPrecio(producto.getPrecio())){
            System.err.println("ERROR: El precio debe ser mayor que 0");
            return false;
        }

        //Validar existencias.
        if (!validarExistencias(producto.getStock())){
            System.err.println("ERROR: El stock o las existencias no pueden ser negativas");
            return false;
        }

        //Si todo correcto, insertamos.
        return productoService.insertar(producto);
    }

    //Obtiene todos los productos ordenados por descripción
    public List<Producto> obtenerTodos(){
        return producto.obtenerTodos();
    }

    //Busca un producto por su código o ID.
    public Producto buscarPorCodigo(String codProducto){
        if (!validarCoddigo(codProducto)){
            System.err.println("ERROR: El código dle procuto no es válido");
            return null;
        }
        return productoService.buscarPorCodigo(codProducto);
    }

    //Actualizar los datos de un producto
    public boolean actualizarProdcuto(Producto producto){
        //Las mismas validaciones que insertar.
        //Validar Código.
        if(!validarCoddigo(producto.getIdProducto())){
            System.err.println("ERROR: El código o el ID del producto es obligatorio");
            return false;
        }

        //Validar descripción
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()){
            System.err.println("ERROR: La descirpción del producto ess obligatoria.");
            return false;
        }

        //Validar precio.
        if (!validarPrecio(producto.getPrecio())){
            System.err.println("ERROR: El precio debe ser mayor que 0");
            return false;
        }

        //Validar existencias.
        if (!validarExistencias(producto.getStock())){
            System.err.println("ERROR: El stock o las existencias no pueden ser negativas");
            return false;
        }

        return productoService.actualizar(producto);
    }

    //Eliminar un producto de la base de datos.
    public boolean eliminarProducto(String codProducto){
        if (!validarCoddigo(codProducto)){
            System.err.println("ERROR: Código del producto no válido");
            return false;
        }
        return productoService.eliminar(codProducto);
    }

    //Verificar si un producto existe en la base de datos.
    public boolean existeProducto(String codProducto){
        return buscarPorCodigo(codProducto) !=null;
    }

    //Verificar si hay suficiente stock para una venta.
    public boolean hayStockDisponble(String codProducto, int cantidadSolicitada){
        Producto producto = buscarPorCodigo(codProducto);

        if (producto == null){
            System.err.println("ERROR: El producto no existe");
            return false;
        }

        if (producto.getStock() < cantidadSolicitada){
            System.err.println("ERROR: Stock o existencias insuficientes. Disponible: "+ producto.getStock()+", Solicitado: "+cantidadSolicitada);
            return false;
        }

        return true;
    }

    //Actualiza el stock de un producto tras una venta.
    public boolean actualizarStock(String codProducto, int cantidadVendida){
        //Verificar que haya suficiente stock
        if (!hayStockDisponble(codProducto, cantidadVendida)){
            return false;
        }

        return productoService.actulizarStock(codProducto, cantidadVendida);
    }
}
