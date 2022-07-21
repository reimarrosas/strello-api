package me.reimarrosas.strelloapi.config;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.security.CustomAuthenticationEntryPoint;
import me.reimarrosas.strelloapi.security.CustomDsl;
import me.reimarrosas.strelloapi.security.HttpStatusHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .apply(CustomDsl.customDsl())
                .and()
                .exceptionHandling(ex -> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .logout(l -> l
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(new HttpStatusHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .build();
    }
}
