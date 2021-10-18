package com.maga.com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdottoException extends RuntimeException{

    public ProdottoException(String message){
        super(message);
    }
}
