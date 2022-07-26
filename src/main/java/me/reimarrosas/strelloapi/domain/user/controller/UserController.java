package me.reimarrosas.strelloapi.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.domain.user.dto.UserSignupDto;
import me.reimarrosas.strelloapi.domain.user.service.UserService;
import me.reimarrosas.strelloapi.utils.ResponsePayload;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponsePayload signupUser(@Valid @RequestBody UserSignupDto signupDto) {
        userService.createUser(signupDto);
        return ResponsePayload.builder()
                .message("User signup successful!")
                .success(true)
                .build();
    }
}
