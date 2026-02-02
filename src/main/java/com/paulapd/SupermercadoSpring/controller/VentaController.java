package com.paulapd.SupermercadoSpring.controller;

import com.paulapd.SupermercadoSpring.DTO.VentaCreateDTO;
import com.paulapd.SupermercadoSpring.DTO.VentaResponseDTO;
import com.paulapd.SupermercadoSpring.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crear(@RequestBody VentaCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ventaService.crearVenta(dto));
    }
}
}
