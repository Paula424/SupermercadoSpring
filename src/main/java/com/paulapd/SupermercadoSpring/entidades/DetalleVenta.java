package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
