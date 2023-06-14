package com.api.kimi.error.Producto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNombreNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 4387651062329728573L;

    public ProductoNombreNotFoundException(String name) {
        super("No se ha podido encontrar el producto cuyo nombre es : " + name);
    }

}
