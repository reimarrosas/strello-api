package me.reimarrosas.strelloapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.reimarrosas.strelloapi.domain.user.dto.UserLoginDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonAuthFilter extends UsernamePasswordAuthenticationFilter {
    public JsonAuthFilter(AuthenticationManager authenticationManager, String processUrl) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl(processUrl);
        setAuthenticationSuccessHandler(new HttpStatusHandler());
        setAuthenticationFailureHandler(new HttpStatusHandler());
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported!");
        }

        var loginDto = getLoginDto(request);
        var authRequest = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        setDetails(request, authRequest);
        return getAuthenticationManager().authenticate(authRequest);
    }

    private UserLoginDto getLoginDto(HttpServletRequest request) {
        var loginDto = new UserLoginDto();
        try {
            var reader = request.getReader();
            var mapper = new ObjectMapper();
            loginDto = mapper.readValue(reader, UserLoginDto.class);
        } catch (Exception e) {
            throw new AuthenticationServiceException("Invalid authentication payload!");
        }

        return loginDto;
    }
}
