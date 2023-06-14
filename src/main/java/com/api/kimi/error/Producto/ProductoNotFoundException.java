package com.api.kimi.error.Producto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductoNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 7537733698844785644L;
    public ProductoNotFoundException(Long id){
        super("No se ha podido encontrar el producto cuyo id es: " + id);
    }
}
