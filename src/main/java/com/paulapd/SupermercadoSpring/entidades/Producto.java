package com.paulapd.SupermercadoSpring.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @JsonIgnoreProperties("producto")
    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> detalleVentas = new ArrayList<>();

//el ManyToMany, no hay que ponerlo en el programa ya que en el programa tenemos una entidad intermedia DetalleVenta.

}
