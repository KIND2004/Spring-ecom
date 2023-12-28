package com.xrontech.spring.ecom.domain.security;

import com.xrontech.spring.ecom.domain.user.User;
import com.xrontech.spring.ecom.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public ResponseEntity<?> userSignUp(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByEmail(registerRequestDTO.getEmail().toLowerCase()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist");
        } else if (userRepository.findByMobile(registerRequestDTO.getMobile()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile Already Exist");
        } else {
            User user = new User();
            user.setFirstName(registerRequestDTO.getFirstName());
            user.setLastName(registerRequestDTO.getLastName());
            user.setEmail(registerRequestDTO.getEmail().toLowerCase());
            user.setMobile(registerRequestDTO.getMobile());
            user.setPassword(registerRequestDTO.getPassword());

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully!");
        }
    }

    public ResponseEntity<?> userLogIn(LogInRequestDTO logInRequestDTO) {
        if (userRepository.findByEmailAndPassword(logInRequestDTO.getEmail(), logInRequestDTO.getPassword()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("User Logged Successfully!");
        }
    }
}
