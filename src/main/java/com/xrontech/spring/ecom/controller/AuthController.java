package com.xrontech.spring.ecom.controller;

import com.xrontech.spring.ecom.dto.LogInRequestDTO;
import com.xrontech.spring.ecom.dto.RegisterRequestDTO;
import com.xrontech.spring.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> userSignUp(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.userSignUp(registerRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogIn(@RequestBody LogInRequestDTO logInRequestDTO){
        return authService.userLogIn(logInRequestDTO);
    }
}
