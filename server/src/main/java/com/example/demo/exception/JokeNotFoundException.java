package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "JokeRequest Not Found")
public class JokeNotFoundException extends RuntimeException {
    public JokeNotFoundException(String errorMessage){
        super(errorMessage);
    }

    @Override
    public String getMessage(){
        return super.getMessage();
    }
}
