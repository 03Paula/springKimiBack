package com.api.kimi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "productos")
public class Producto {
    private static final long serialVersionUID = 9044114950596773764L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String categoria;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private String imagen;

    @Column( length = 1000 ,nullable = false)
    private String descripcion;

    @Column( length = 50 ,nullable = false)
    private String tamanio;

    @Column(length = 100, nullable = false)
    private String autor;

    @Column(length = 100,nullable = false)
    private String generoSerie;

    @Column(length = 100,nullable = false)
    private String formatoColeccion;

    @Column(nullable = false)
    @JsonFormat(pattern="dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private String fechaSalida;

    @Column(nullable = true)
    private Integer paginas;

}
