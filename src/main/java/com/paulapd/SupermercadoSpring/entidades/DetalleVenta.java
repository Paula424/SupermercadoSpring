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

    //Hacer relaciones id_venta

    //Hacer relaciones id_producto

}
