package com.api.kimi.dto.carrito;

import com.api.kimi.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarritoDTO {
    private Long id;
    private Long usuarioId;
    private List<Producto> productos;
    private Integer cantidad;
    private Double precioTotal;
}
