package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.dto.DetalleVentaDTO;
import com.paulapd.SupermercadoSpring.dto.VentaCreateDTO;
import com.paulapd.SupermercadoSpring.dto.VentaResponseDTO;
import com.paulapd.SupermercadoSpring.mapper.VentaMapper;
import com.paulapd.SupermercadoSpring.entidades.Cliente;
import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;
import com.paulapd.SupermercadoSpring.entidades.Producto;
import com.paulapd.SupermercadoSpring.entidades.Venta;
import com.paulapd.SupermercadoSpring.repositorio.ClienteRepositorio;
import com.paulapd.SupermercadoSpring.repositorio.ProductoRepositorio;
import com.paulapd.SupermercadoSpring.repositorio.VentaRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
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
//        LocalDateTime ahora = LocalDateTime.now();
        venta.setFechaInicio(LocalDateTime.now()); //empieza la venta
//        venta.setFechaFin(ahora);//si ya está confirmada, se marca fin
        venta.setMetodoPago(dto.metodoPago());

        double total = 0;
        List<DetalleVenta> detalles = new ArrayList<>();

        //Procesar detalles.
        for (DetalleVentaDTO detDto : dto.detalleVentaDTOS()) {
//            if (detDto.cantidad() <=0){
//                throw new RuntimeException(("La cantidad debe ser mayor que 0"));
//            }
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

            venta.getDetallesVentas().add(detalleVenta);
//            double subtotal = detDto.cantidad() * producto.getPrecio();
//            total += subtotal;
//            detalles.add(detalleVenta);
            total += detalleVenta.getSubTotal();
        }

//        venta.setDetallesVentas(detalles);
        venta.setTotal(total);
        venta.setFechaFin(LocalDateTime.now());

        Venta ventaGuardada = ventaRepositorio.save(venta);
        return VentaMapper.toResponseDTO(ventaGuardada);
    }

    @Override
    public Page<VentaResponseDTO> listarVentas(Pageable pageable){
        return ventaRepositorio.findAll(pageable)
                .map(VentaMapper::toResponseDTO);
    }

    @Override
    public Page<VentaResponseDTO> listarVentasPorCliente(Long clienteId, Pageable pageable){
        return ventaRepositorio.findByCliente_IdCliente(clienteId, pageable)
                .map(VentaMapper::toResponseDTO);
    }

}
