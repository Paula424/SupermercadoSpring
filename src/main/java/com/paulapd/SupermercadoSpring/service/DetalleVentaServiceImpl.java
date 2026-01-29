package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;
import com.paulapd.SupermercadoSpring.repositorio.DetalleVentaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl {
    private final DetalleVentaRepositorio detalleVentaRepositorio;
    private final ProductoService productoService;

    public DetalleVentaServiceImpl(){
        this.detalleVentaRepositorio
    }

    //Validar que la cantidad sea mayor que 0.
    public boolean validarCantidad(int cantidad){
        if(cantidad <=0){
            System.err.println("ERROR: La cantidad debe ser mayor que 0");
            return false;
        }
        return true;
    }

    //Validar que el precio unitario sea mayor que 0.
    public boolean validarPrecio(double precioUnitario){
        if (precioUnitario==null||precioUnitario.compareTo(double.ZERO)<=0){
            System.err.println("ERROR: El precio unitario, tiene que ser mayor que 0");
            return false;
        }
        return true;
    }

    //Inserta un nuevo detalle de venta tras validar los datos.
    public boolean insertarDetalle(DetalleVenta detalleVenta){
        //Validar cantidad
        if(!ValidarCantidad(detalleVenta.getCantidad())){
            return false;
        }

        //Validar precioUnitario.
        if(!validarPrecio(detalleVenta.getPrecioUnitario())){
            return false;
        }

        //Verificar que el producto exista
        if (!productoService.exixteProducto(detalleVenta.getProducto())){
            System.err.println("ERROR: El producto "+detalleVenta.getProducto()+ " no existe");
            return false;
        }

        //Si todo esta bien
        return detalleVentaService.insertar(detalleVenta);
    }

    //Obtenemos todos los detalles de una venta específica.
    public List<DetalleVenta> obtenerPorVenta(int codVenta){return detalleVenta.obtenerPorVenta(codVenta); }

    //Eliminamos detalle de una venta.
    public boolean eliminarPorVenta(int codVenta){
        return detalleVenta.eliminarPorVenta(codVenta);
    }

    //Busca un detalle específico por su ID.
    public DetalleVenta buscraPorId(int id){
        return detalleVenta.buscarPorId(id);
    }

    //Calcular importe toral de todos los detalles de una venta.
    public double calcularTotalVenta(int codVenta){
        List<DetalleVenta> detalleVentas = obtenerPorVenta(codVenta);
        double total = double.ZERO;

        for( DetalleVenta detalleVenta : detalleVentas){
            total = total.add(detalleVenta.calcularImporteLinea());
        }

        return total;
    }
}
