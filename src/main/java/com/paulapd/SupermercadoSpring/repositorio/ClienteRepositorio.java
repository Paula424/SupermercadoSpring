package com.paulapd.SupermercadoSpring.repositorio;

import com.paulapd.SupermercadoSpring.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreo(String correo);
}
