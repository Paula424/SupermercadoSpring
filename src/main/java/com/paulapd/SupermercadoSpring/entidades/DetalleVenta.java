package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalleVenta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    private int cantidad;

    private double precioUnitario;

    private double subTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "venta_id")
    private Venta venta;
}
