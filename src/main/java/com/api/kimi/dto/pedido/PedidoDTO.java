package com.api.kimi.dto.pedido;

import com.api.kimi.model.Producto;
import com.api.kimi.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private Long usuarioId;
    private List<Producto> productos;
    private Date fecha_pedido;
    private Double precioTotal;
    private String estado_pedido;

}
