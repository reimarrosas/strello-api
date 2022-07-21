package me.reimarrosas.strelloapi.security;

import me.reimarrosas.strelloapi.utils.HttpJsonResponse;
import me.reimarrosas.strelloapi.utils.ResponsePayload;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpJsonResponse.createJsonResponse(
                response,
                401,
                ResponsePayload.builder()
                        .message("Authentication failed!")
                        .success(false)
                        .payload(authException.getMessage())
                        .build()
        );
    }
}