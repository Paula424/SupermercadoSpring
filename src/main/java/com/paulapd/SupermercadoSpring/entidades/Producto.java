package com.paulapd.SupermercadoSpring.entidades;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String marca;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalleVentas = new ArrayList<>();

    @ManyToMany(mappedBy = "productosSet") //el nombre de productos en ventas.
    private Set<Venta> ventaSet = new HashSet<>();

}
