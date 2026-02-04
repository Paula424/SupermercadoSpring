package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;
import com.paulapd.SupermercadoSpring.repositorio.DetalleVentaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {
    private final DetalleVentaRepositorio detalleVentaRepositorio;
    private final ProductoService productoService;

    public DetalleVentaServiceImpl(DetalleVentaRepositorio detalleVentaRepositorio, ProductoService productoService){
        this.detalleVentaRepositorio = detalleVentaRepositorio;
        this.productoService = productoService;
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
        return precioUnitario > 0;
    }

    //Inserta un nuevo detalle de venta tras validar los datos.
    public DetalleVenta insertarDetalle(DetalleVenta detalleVenta){
        //Validar cantidad
        if(!validarCantidad(detalleVenta.getCantidad())){
            throw new RuntimeException("La cantidad debe ser mayor que 0");
        }

        if(!validarPrecio(detalleVenta.getPrecioUnitario())){
            throw new RuntimeException("El precio unitario debe ser mayor que 0");
        }

        if (!productoService.existeProducto(detalleVenta.getProducto().getIdProducto())){
            throw new RuntimeException("El producto no existe");
        }

        //Si todo esta bien
        return detalleVentaRepositorio.save(detalleVenta);
    }

    //Obtenemos todos los detalles de una venta específica.
    public List<DetalleVenta> obtenerPorVenta(Long idVenta){
        return detalleVentaRepositorio.findByVenta_IdVenta(idVenta);
    }

    //Eliminamos detalle de una venta.
    public void eliminarPorVenta(Long idVenta){
        detalleVentaRepositorio.deleteByVenta_IdVenta(idVenta);
    }

    //Busca un detalle específico por su ID.
    public DetalleVenta buscarPorId(Long id){
        return detalleVentaRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Detalle no encontrado"));
    }

    //Calcular importe toral de todos los detalles de una venta.

    public double calcularTotalVenta(Long idVenta){
        List<DetalleVenta> detalles = obtenerPorVenta(idVenta);
        double total = 0;

        for (DetalleVenta d : detalles){
            total += d.getSubTotal();
        }
        return total;
    }
}
