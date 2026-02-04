package com.paulapd.SupermercadoSpring.controller;

import com.paulapd.SupermercadoSpring.entidades.Categoria;
import com.paulapd.SupermercadoSpring.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.crear(categoria), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }
}