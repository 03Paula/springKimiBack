package com.api.kimi.error.Producto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 5193583714429819960L;

    public CategoryNotFoundException(String categoria) {
        super("No se ha podido encontrar la categoria : " + categoria);
    }
}
