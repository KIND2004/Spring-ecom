package com.xrontech.spring.ecom.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserResource {
    private final UserService userService;

    @PostMapping("/profile-update")
    public ResponseEntity<?> userUpdateProfile(@Valid @RequestBody UserUpdateProfileDTO userUpdateProfileDTO) {
        return userService.userUpdateProfile(userUpdateProfileDTO);
    }

    @PostMapping("/profile-image")
    public ResponseEntity<?> userUpdateProfileImage(@RequestBody MultipartFile file) throws IOException {
        return userService.userUpdateProfileImage(file);
    }
}
