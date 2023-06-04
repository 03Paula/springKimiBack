package com.api.kimi.dto.pedido;

import com.api.kimi.model.Producto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CrearPedidoDTO {
    private Long usuarioId;
    private List<Long> productos;
    private Date fecha_pedido;
    private Double precioTotal;
    private String estado_pedido;

}
