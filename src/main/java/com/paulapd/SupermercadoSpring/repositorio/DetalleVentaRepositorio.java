package com.paulapd.SupermercadoSpring.repositorio;

import com.paulapd.SupermercadoSpring.entidades.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepositorio extends JpaRepository<DetalleVenta, Long> {
}
