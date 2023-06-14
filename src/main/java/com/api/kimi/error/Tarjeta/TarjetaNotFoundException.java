package com.api.kimi.error.Tarjeta;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TarjetaNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 7537733698844785644L;

    public TarjetaNotFoundException(Long id){
        super("No se ha podido encontrar la tarjeta cuya id es: " + id);
    }
}
