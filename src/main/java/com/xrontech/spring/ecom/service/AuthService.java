package com.xrontech.spring.ecom.service;

import com.xrontech.spring.ecom.dto.LogInRequestDTO;
import com.xrontech.spring.ecom.dto.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<?> userSignUp(RegisterRequestDTO registerRequestDTO);

    ResponseEntity<?> userLogIn(LogInRequestDTO logInRequestDTO);
}
