package me.reimarrosas.strelloapi.exceptionhandler;

import me.reimarrosas.strelloapi.exception.HttpForbiddenException;
import me.reimarrosas.strelloapi.exception.HttpNotFoundException;
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

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpForbiddenException.class)
    public ResponsePayload handleForbiddenException(HttpForbiddenException ex) {
        return ResponsePayload.builder()
                .message("Access forbidden!")
                .success(false)
                .payload(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpNotFoundException.class)
    public ResponsePayload handleNotFoundException(HttpNotFoundException ex) {
        return ResponsePayload.builder()
                .message("Resource not found!")
                .success(false)
                .payload(ex.getMessage())
                .build();
    }
}
