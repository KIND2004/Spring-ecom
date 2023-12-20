package com.xrontech.spring.ecom.service;

import com.xrontech.spring.ecom.dto.UserUpdateProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public ResponseEntity<?> userUpdateProfile(UserUpdateProfileDTO userUpdateProfileDTO);
}
