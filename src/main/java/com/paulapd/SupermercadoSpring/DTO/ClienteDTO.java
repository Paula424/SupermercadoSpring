package com.paulapd.SupermercadoSpring.DTO;

import java.time.LocalDateTime;

public record ClienteDTO (
        Long idCliente,
        String nombre,
        String apellido,
        String dni,
        String telefono,
        String correo,
        String direccion,
        LocalDateTime fechaRegistro
){}

