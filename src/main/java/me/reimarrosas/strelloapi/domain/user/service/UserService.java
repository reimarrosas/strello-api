package me.reimarrosas.strelloapi.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.domain.user.dto.UserSignupDto;
import me.reimarrosas.strelloapi.domain.user.entity.UserEntity;
import me.reimarrosas.strelloapi.domain.user.mapper.UserSignupMapper;
import me.reimarrosas.strelloapi.domain.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(UserSignupDto signupDto) {
        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        return userRepository.save(UserSignupMapper.INSTANCE.srcToDest(signupDto));
    }

    public static UserEntity getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (UserEntity) principal;
    }
}
