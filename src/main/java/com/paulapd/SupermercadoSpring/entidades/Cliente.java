package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cliente")
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
