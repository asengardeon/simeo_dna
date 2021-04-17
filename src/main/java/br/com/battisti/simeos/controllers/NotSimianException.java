package br.com.battisti.simeos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotSimianException extends RuntimeException{

    public NotSimianException(String message) {
        super(message);
    }
}
