package com.paulapd.SupermercadoSpring.repositorio;

import com.paulapd.SupermercadoSpring.entidades.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepositorio extends JpaRepository<Venta, Long> {
}
