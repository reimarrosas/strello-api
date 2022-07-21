package me.reimarrosas.strelloapi.exceptionhandler;

import me.reimarrosas.strelloapi.utils.ResponsePayload;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class HttpExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponsePayload handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            errors.put(
                    ((FieldError) err).getField(),
                    err.getDefaultMessage()
            );
        });

        return ResponsePayload.builder()
                .message("Malformed signup payload!")
                .success(false)
                .payload(errors)
                .build();
    }
}
