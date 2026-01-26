package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private LocalDateTime fecha;
    private double total;
    private String metodoPago;

    //Relaciones con id_clinte.

}
