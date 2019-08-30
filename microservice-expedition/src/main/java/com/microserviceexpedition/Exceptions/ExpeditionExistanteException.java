package com.microserviceexpedition.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpeditionExistanteException extends RuntimeException {


    public ExpeditionExistanteException(String message) {
        super(message);
    }
}
