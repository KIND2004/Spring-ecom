package com.xrontech.spring.ecom.service;

import com.xrontech.spring.ecom.dto.UserLogInRequestDTO;
import com.xrontech.spring.ecom.dto.UserRegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<?> userSignUp(UserRegisterRequestDTO userRegisterRequestDTO);

    ResponseEntity<?> userLogIn(UserLogInRequestDTO userLogInRequestDTO);
}
