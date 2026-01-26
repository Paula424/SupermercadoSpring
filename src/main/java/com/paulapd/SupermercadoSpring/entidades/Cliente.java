package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Entity
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;

    private String dni;

    private Integer telefono;

    private String correo;

    private String direccion;

    private LocalDateTime fecha_registro;


}
