package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.DTO.DetalleVentaDTO;
import com.paulapd.SupermercadoSpring.DTO.ProductoDTO;
import com.paulapd.SupermercadoSpring.DTO.VentaDTO;
import com.paulapd.SupermercadoSpring.entidades.Cliente;
import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;
import com.paulapd.SupermercadoSpring.entidades.Producto;
import com.paulapd.SupermercadoSpring.entidades.Venta;
import com.paulapd.SupermercadoSpring.repositorio.ClienteRepositorio;
import com.paulapd.SupermercadoSpring.repositorio.ProductoRepositorio;
import com.paulapd.SupermercadoSpring.repositorio.VentaRepositorio;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    private final VentaRepositorio ventaRepositorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ProductoRepositorio productoRepositorio;

    public VentaServiceImpl(VentaRepositorio ventaRepositorio, ClienteRepositorio clienteRepositorio, ProductoRepositorio productoRepositorio){
        this.ventaRepositorio = ventaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDTO){
        //Validar cliente
        Cliente cliente = clienteRepositorio.findById(ventaDTO.cliente_id().idCliente())
                .orElseThrow(()-> new RuntimeException("El Cliente no existe"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now());
        venta.setMetodoPago(ventaDTO.metodoPago());

        double total =0;
        List<DetalleVenta> detalles = new ArrayList<>();

        //Procesar cada detalle.
        for (DetalleVentaDTO dto : ventaDTO.detalles()){
            Producto producto = productoRepositorio.findById(dto.idProducto())
                    .orElseThrow(()-> new RuntimeException("El Producto no existe"));

            //Regla de negocio
            if (producto.getStock() < dto.cantidad()){
                throw new RuntimeException("Stock insuficiente para el producto");
            }

            //Restar Stock
            producto.setStock(producto.getStock()-dto.cantidad());

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(dto.cantidad());
            detalleVenta.setPrecioUnitario(producto.getPrecio());

            double subtotal = dto.cantidad() * producto.getPrecio();
            detalleVenta.setSubTotal(subtotal);
            detalleVenta.setVenta(venta);

            total += subtotal;
            detalles.add(detalleVenta);
        }

        venta.setDetallesVentas(detalles);
        venta.setTotal(total);

        ventaRepositorio.save(venta);

        return ventaDTO;


    }

}
