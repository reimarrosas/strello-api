package me.reimarrosas.strelloapi.security;

import me.reimarrosas.strelloapi.utils.ResponsePayload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpStatusHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler, LogoutSuccessHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ResponsePayload.createJsonResponse(
                response,
                401,
                ResponsePayload.builder()
                        .message("Authentication failed!")
                        .success(false)
                        .payload(exception.getMessage())
                        .build()
        );
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponsePayload.createJsonResponse(
                response,
                200,
                ResponsePayload
                        .builder()
                        .message("Authentication successful!")
                        .success(true)
                        .build()
        );
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        ResponsePayload.createJsonResponse(
                response,
                200,
                ResponsePayload
                        .builder()
                        .message("Logout successful!")
                        .success(true)
                        .build()
        );
    }
}
