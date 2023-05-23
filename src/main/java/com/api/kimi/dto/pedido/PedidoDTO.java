package com.api.kimi.dto.pedido;

import com.api.kimi.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private Usuario id_usuario;
    private List<Long> id_productos;
    private Date fecha_pedido;
    private Double precio_total;
    private String estado_pedido;

}
