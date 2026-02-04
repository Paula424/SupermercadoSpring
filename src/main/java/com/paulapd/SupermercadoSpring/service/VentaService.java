package com.paulapd.SupermercadoSpring.service;

import com.paulapd.SupermercadoSpring.dto.VentaCreateDTO;
import com.paulapd.SupermercadoSpring.dto.VentaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VentaService {
    VentaResponseDTO crearVenta(VentaCreateDTO dto);
    Page<VentaResponseDTO> listarVentas(Pageable pageable);
    Page<VentaResponseDTO> listarVentasPorCliente(Long idCliente, Pageable pageable);

}
