package com.api.kimi.error.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 7523698996940110262L;
    public EmailNotFoundException(String email) {
        super("No se ha podido encontrar el usuario cuyo email es: " + email);
    }
}
