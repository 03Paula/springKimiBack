package com.api.kimi.dto.producto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Date;

@Getter
@Setter
public class CrearProductoDTO {
    private String categoria;
    private String nombre;
    private Float precio;
    private Integer cantidad;
    private String imagen;
    private String descripcion;
    private String tamanio;
    private String autor;
    private String generoSerie;
    private String formatoColeccion;
    @JsonFormat(pattern="dd/MM/yyyy" , shape = JsonFormat.Shape.STRING)
    private String fechaSalida;
    private Integer paginas;
}
