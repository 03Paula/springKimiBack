package com.api.kimi.error.Direccion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DireccionNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -1782160032445168116L;
    public DireccionNotFoundException(Long id){
        super("No se ha podido encontrar la direccion cuya id es: " + id);
    }
}
