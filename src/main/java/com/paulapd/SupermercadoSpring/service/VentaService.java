package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.DTO.VentaCreateDTO;
import com.paulapd.SupermercadoSpring.DTO.VentaResponseDTO;

public interface VentaService {
    VentaResponseDTO crearVenta(VentaCreateDTO dto);
}
