package com.xrontech.spring.ecom.controller;

import com.xrontech.spring.ecom.dto.UserUpdateProfileDTO;
import com.xrontech.spring.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/profile-update")
    public ResponseEntity<?> userUpdateProfile(@RequestBody UserUpdateProfileDTO userUpdateProfileDTO) {
        return userService.userUpdateProfile(userUpdateProfileDTO);
    }
}
