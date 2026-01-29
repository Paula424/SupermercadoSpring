package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombre;
    private String apellido;

    private String dni;

    private String telefono;

    private String correo;

    private String direccion;

    private LocalDateTime fechaRegistro;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta> ventasCli = new ArrayList<>();


}
