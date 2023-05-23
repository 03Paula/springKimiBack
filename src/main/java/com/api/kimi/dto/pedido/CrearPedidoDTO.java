package com.api.kimi.dto.pedido;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CrearPedidoDTO {
    private Long id_cliente;
    private List<Long> id_productos;
    private Date fecha_pedido;
    private Double precio_total;
    private String estado_pedido;

}
