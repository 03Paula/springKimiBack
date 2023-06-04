package com.api.kimi.dto.carrito;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CrearCarritoDTO {
    private Long usuarioId;
    private List<Long> productos;
    private Integer cantidad = 1;
    private Double precioTotal;
}
