package me.reimarrosas.strelloapi.domain.user.service;

import lombok.RequiredArgsConstructor;
import me.reimarrosas.strelloapi.domain.user.dto.UserSignupDto;
import me.reimarrosas.strelloapi.domain.user.entity.UserEntity;
import me.reimarrosas.strelloapi.domain.user.mapper.UserSignupMapper;
import me.reimarrosas.strelloapi.domain.user.repository.UserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserSignupMapper mapper = Mappers.getMapper(UserSignupMapper.class);

    private final PasswordEncoder passwordEncoder;

    public UserEntity createUser(UserSignupDto signupDto) {
        signupDto.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        return userRepository.save(mapper.srcToDest(signupDto));
    }

    public List<UserEntity> getAllUsers() {
        var users = new ArrayList<UserEntity>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
