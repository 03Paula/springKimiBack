package com.api.kimi.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DireccionUsuarioNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -7027355697483364226L;
    public DireccionUsuarioNotFoundException(Long id) {
        super("No se ha podido encontrar la direccion cuyo id de usuario es : " + id);
    }
}
