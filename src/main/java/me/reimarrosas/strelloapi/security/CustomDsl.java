package me.reimarrosas.strelloapi.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity builder) throws Exception {
        var authManager = builder.getSharedObject(AuthenticationManager.class);
        builder.addFilterAt(
                new JsonAuthFilter(authManager, "/api/auth/login"),
                UsernamePasswordAuthenticationFilter.class);
    }

    public static CustomDsl customDsl() {
        return new CustomDsl();
    }
}
