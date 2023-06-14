package com.api.kimi.error.Carrito;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarritoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 9160204107679493170L;
    public CarritoNotFoundException(Long id){
        super("No se ha podido encontrar el carrito cuya id es: " + id);
    }

}
