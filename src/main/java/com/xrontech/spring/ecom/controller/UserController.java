package com.xrontech.spring.ecom.controller;

import com.xrontech.spring.ecom.dto.UserLogInRequestDTO;
import com.xrontech.spring.ecom.dto.UserRegisterRequestDTO;
import com.xrontech.spring.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> userSignUp(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.userSignUp(userRegisterRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogIn(@RequestBody UserLogInRequestDTO userLogInRequestDTO){
        return userService.userLogIn(userLogInRequestDTO);
    }
}
