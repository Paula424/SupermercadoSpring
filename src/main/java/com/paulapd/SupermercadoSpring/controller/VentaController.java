package com.paulapd.SupermercadoSpring.controller;

import com.paulapd.SupermercadoSpring.dto.VentaCreateDTO;
import com.paulapd.SupermercadoSpring.dto.VentaResponseDTO;
import com.paulapd.SupermercadoSpring.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(@RequestBody VentaCreateDTO dto){
        return new ResponseEntity<>(ventaService.crearVenta(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public Page<VentaResponseDTO> listarVentas(Pageable pageable){
        return ventaService.listarVentas(pageable);
    }

    @GetMapping("/cliente/{id}")
    public Page<VentaResponseDTO> listarPorCliente(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ventaService.listarVentasPorCliente(id, pageable);
    }
}

