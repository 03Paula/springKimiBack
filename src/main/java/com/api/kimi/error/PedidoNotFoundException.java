package com.api.kimi.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -6484191445035935766L;
    public PedidoNotFoundException(Long id) {
        super("No se ha podido encontrar el pedido cuyo id es: " + id);
    }
}
