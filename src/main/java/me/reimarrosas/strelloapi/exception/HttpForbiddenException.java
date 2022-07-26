package me.reimarrosas.strelloapi.exception;

public class HttpForbiddenException extends RuntimeException {
    public HttpForbiddenException(String message) {
        super(message);
    }
}
