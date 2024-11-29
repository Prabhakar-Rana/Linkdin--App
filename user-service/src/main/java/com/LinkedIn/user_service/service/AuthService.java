package com.LinkedIn.user_service.service;

import com.LinkedIn.user_service.dto.LoginRequestDto;
import com.LinkedIn.user_service.dto.LoginResponseDto;
import com.LinkedIn.user_service.dto.SignUpDto;
import com.LinkedIn.user_service.dto.UserDto;
import com.LinkedIn.user_service.entity.User;
import com.LinkedIn.user_service.repository.UserRepository;
import com.LinkedIn.user_service.util.JWTService;
import com.LinkedIn.user_service.util.PasswordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    public  final ModelMapper modelMapper;
    public final PasswordService passwordService;
    public final UserRepository userRepository;
    public final JWTService jwtService;
    @Autowired
    private LoginResponseDto loginResponseDto;
    public UserDto signUp(SignUpDto signUpDto){
        if(userRepository.findByEmail(signUpDto.getEmail()).isPresent()){
            throw new IllegalStateException("Email already taken!");
        }
        String encodedPassword = passwordService.encodePassword(signUpDto.getPassword());
        User user = modelMapper.map(signUpDto, User.class);
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        System.out.println("savedUser="+savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto){
        Optional<Object> userOptional = userRepository.findByEmail(loginRequestDto.getEmail());
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("Email not found!");
        }

        User user = (User) userOptional.get();

        // Check if the raw password matches the encoded password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Incorrect password!");
        }

        String token = jwtService.generateAccessToken(user);
        System.out.println("Token="+token);
        loginResponseDto.setAccessToken(token);
        return loginResponseDto;
    }
}
