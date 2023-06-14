package com.api.kimi.error.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailDuplicatedException extends RuntimeException{
    private static final long serialVersionUID = 5920982302277683579L;
    public EmailDuplicatedException(String email){
        super("El email que ha introducido ya se encuentra en la base de datos");
    }

}
