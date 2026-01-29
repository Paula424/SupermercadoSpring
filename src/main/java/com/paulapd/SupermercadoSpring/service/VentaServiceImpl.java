package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.DTO.DetalleVentaDTO;
import com.paulapd.SupermercadoSpring.DTO.VentaCreateDTO;
import com.paulapd.SupermercadoSpring.DTO.VentaResponseDTO;
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

    public VentaServiceImpl(VentaRepositorio ventaRepositorio, ClienteRepositorio clienteRepositorio, ProductoRepositorio productoRepositorio) {
        this.ventaRepositorio = ventaRepositorio;
        this.clienteRepositorio = clienteRepositorio;
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public VentaResponseDTO crearVenta(VentaCreateDTO dto) {
        //Validar cliente
        Cliente cliente = clienteRepositorio.findById(dto.clienteID())
                .orElseThrow(() -> new RuntimeException("El Cliente no existe"));

        if (dto.detalleVentaDTOS() == null || dto.detalleVentaDTOS().isEmpty()){
            throw new RuntimeException("La venta debe tener al menos un producto");
        }

        //Crear Venta
        Venta venta = new Venta();
        venta.setCliente(cliente);
        LocalDateTime ahora = LocalDateTime.now();
        venta.setFechaInicio(ahora); //empieza la venta
        venta.setFechaFin(ahora);//si ya está confirmada, se marca fin
        venta.setMetodoPago(dto.metodoPago());

        double total = 0;
        List<DetalleVenta> detalles = new ArrayList<>();

        //Procesar detalles.
        for (DetalleVentaDTO detDto : dto.detalleVentaDTOS()) {
            if (detDto.cantidad() <=0){
                throw new RuntimeException(("La cantidad debe ser mayor que 0"));
            }
            Producto producto = productoRepositorio.findById(detDto.idProducto())
                    .orElseThrow(() -> new RuntimeException("El Producto no existe"));

            if (producto.getStock()<detDto.cantidad()){
                throw new RuntimeException("Stock insuficiente para el producto: "+producto.getNombre());
            }

            //Restar Stock
            producto.setStock(producto.getStock()-detDto.cantidad());
            productoRepositorio.save(producto);

            //Crear detalle
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(detDto.cantidad());
            detalleVenta.setPrecioUnitario(producto.getPrecio());
            detalleVenta.setVenta(venta);

            double subtotal = detDto.cantidad() * producto.getPrecio();
            total += subtotal;
            detalles.add(detalleVenta);
        }

        venta.setDetallesVentas(detalles);
        venta.setTotal(total);

        Venta ventaGuardada = ventaRepositorio.save(venta);
        return VentaMapper.toResponseDTO(ventaGuardada);
    }

    public boolean validarDescuento(double descuento){
        if(descuento ==null){
            return true; //descuento es opcional (puede ser null o 0)
        }

        if (descuento.compareTo(double.ZERO) <0 || descuento.compareTo(new double("100")) >0){
            System.err.println("ERROR: El descuento debe estar entre 0% y 100% ");
            return false;
        }
        return true;
    }

    public boolean validarPrecio(double precioRecomendado, double precioVenta){
        if (precioRecomendado == null || pecioVenta == null){
            System.err.println("ERROR: Los precios no pueden ser nulos");
            return false;
        }

        // Calcular el rango permitido (±20%)
        double margen = new double("0.20");
        double precioMin = precioRecomendado.multiply(double.ONE.subtract(margen));
        double precioMax = precioRecomendado.multiply(double.ONE.add(margen));

        if (precioVenta.compareTo(precioMin) < 0 || precioVenta.compareTo(precioMax) > 0) {
            System.err.println("Error: El precio de venta debe estar entre " + precioMin + "€ y " + precioMax + "€");
            System.err.println("   (±20% del precio recomendado: " + precioRecomendado + "€)");
            return false;
        }

        return true;
    }

    //Obtiene todas las ventas
    public List<Venta> obtenerTodas(){
        return ventasService.obtenerTodas();
    }

    //Busca una venta por su codigo.
    public  Venta buscarPorCodigo(int codVenta){
        return ventasService.buscarPorCodigo(codVenta);
    }

    //Obtiene todas las ventas de un cliente.
    public List<Venta> obtenerPorCliente(String dniCli){
        return ventasService.obtenerPorCliente(dniCli);
    }

    //Obtiene las ventas en un rango de fechas.
    public List<Venta> obtenerPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        retrun ventasService.obtenerPorFecha(fechaInicio, fechaFin);
    }

    //ELimina una venta y todos sus detalles.
    public boolean eliminarVenta(int codVenta){
        //Primero eliminar los detalles
        detalleVentaService.eliminarPorVenta(codVenta);

        //Luego eliminar la venta
        return ventasService.eliminar(codVenta);
    }
}
