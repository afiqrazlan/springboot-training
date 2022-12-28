package com.example.xeTraining.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WandNotFoundException extends RuntimeException{
    public WandNotFoundException(String message) {
        super(message);
    }

    public WandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
