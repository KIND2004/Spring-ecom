package com.xrontech.spring.ecom.domain.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthResource {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> userSignUp(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.userSignUp(registerRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogIn(@Valid @RequestBody LogInRequestDTO logInRequestDTO) {
        return authService.userLogIn(logInRequestDTO);
    }
}
