package com.smartpath.datacollector.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {
    @ExceptionHandler(value = ProviderNotFoundException.class)
    public ResponseEntity<Object> exception(ProviderNotFoundException exception){
        return new ResponseEntity<>("Provider For This Product NotFound", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ProviderNotRespondException.class)
    public ResponseEntity<Object> providerNotRespond(ProviderNotRespondException exception){
        return new ResponseEntity<>("Provider Not Respond At Moment Please Try Again", HttpStatus.NOT_FOUND);
    }
}
