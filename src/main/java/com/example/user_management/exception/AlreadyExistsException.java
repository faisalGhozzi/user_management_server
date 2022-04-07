package com.example.user_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends Exception{
    private static final long version = 1L;
    public AlreadyExistsException(String message){
        super(message);
    }
}