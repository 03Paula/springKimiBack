package com.api.kimi.error.Producto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -2991287757467969565L;

    public AuthorNotFoundException(String author) {
        super("No se ha podido encontrar productos cuyo autor es: " + author);
    }
}
