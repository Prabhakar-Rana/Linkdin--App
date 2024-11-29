package com.LinkedIn.user_service.controller;

import com.LinkedIn.user_service.dto.LoginRequestDto;
import com.LinkedIn.user_service.dto.LoginResponseDto;
import com.LinkedIn.user_service.dto.SignUpDto;
import com.LinkedIn.user_service.dto.UserDto;
import com.LinkedIn.user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    public final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto>signUp(@RequestBody SignUpDto signUpDto){
        System.out.println("i am in this field");
        try {
            log.error("i am here");
            System.out.println("i am in signup !");
            return ResponseEntity.ok(authService.signUp(signUpDto));
        } catch (Error e){
            System.out.println("i am getting error="+e);
            return null;
        }
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        System.out.println("i am in sigIn !");
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

}
