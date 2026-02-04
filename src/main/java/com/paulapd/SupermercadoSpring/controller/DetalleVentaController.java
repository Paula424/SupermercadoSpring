package com.paulapd.SupermercadoSpring.controller;

import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;
import com.paulapd.SupermercadoSpring.service.DetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping("/venta/{id}")
    public List<DetalleVenta> listarPorVenta(@PathVariable Long id){
        return detalleVentaService.obtenerPorVenta(id);
    }
}