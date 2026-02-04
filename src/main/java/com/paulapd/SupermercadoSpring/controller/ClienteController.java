package com.paulapd.SupermercadoSpring.controller;

import com.paulapd.SupermercadoSpring.dto.ClienteDTO;
import com.paulapd.SupermercadoSpring.entidades.Cliente;
import com.paulapd.SupermercadoSpring.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.guardarCliente(cliente), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.obtenerTodos();
    }

    @GetMapping("/{dni}")
    public ClienteDTO buscarPorDni(@PathVariable String dni) {
        return clienteService.buscarPorDNI(dni);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable String dni) {
        clienteService.eliminarCliente(dni);
        return ResponseEntity.noContent().build();
    }
}