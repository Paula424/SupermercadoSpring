package com.paulapd.SupermercadoSpring.dto;

import java.time.LocalDate;

public record ClienteDTO (
        Long idCliente,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String correo,
        String direccion,
        LocalDate fechaRegistro
){}

