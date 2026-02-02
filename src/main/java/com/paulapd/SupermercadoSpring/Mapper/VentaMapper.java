package com.paulapd.SupermercadoSpring.Mapper;

import com.paulapd.SupermercadoSpring.DTO.ClienteDTO;
import com.paulapd.SupermercadoSpring.DTO.DetalleVentaDTO;
import com.paulapd.SupermercadoSpring.DTO.VentaResponseDTO;
import com.paulapd.SupermercadoSpring.entidades.Venta;

import java.util.List;
import java.util.stream.Collectors;

public class VentaMapper {

    //Convierte una entidad Venta a un DTO para la respuesta.
    public static VentaResponseDTO toResponseDTO(Venta venta){
        //Convertir cada detalle de venta a DetalleVentaDTO
        List<DetalleVentaDTO> detalleVentaDTOS = venta.getDetallesVentas()
                .stream()
                .map(d -> new DetalleVentaDTO(
                        d.getIdDetalle(),
                        d.getCantidad(),
                        d.getPrecioUnitario(),
                        d.getProducto().getIdProducto()
                )).collect(Collectors.toList());

        //Mapear cliente a ClienteDTO
        ClienteDTO clienteDTO = new ClienteDTO(
                venta.getCliente().getIdCliente(),
                venta.getCliente().getNombre(),
                venta.getCliente().getApellido(),
                venta.getCliente().getDni(),
                venta.getCliente().getTelefono(),
                venta.getCliente().getCorreo(),
                venta.getCliente().getDireccion(),
                venta.getCliente().getFechaRegistro()
        );
        //Crear el DTO de VentaResponse
        return new VentaResponseDTO(
                venta.getIdVenta(),
                venta.getFechaInicio(),
                venta.getFechaFin(),
                venta.getTotal(),
                venta.getMetodoPago(),
                detalleVentaDTOS,
                clienteDTO
        );

    }
}
