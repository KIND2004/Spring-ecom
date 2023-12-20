package com.xrontech.spring.ecom.service.serviceImpl;

import com.xrontech.spring.ecom.dto.LogInRequestDTO;
import com.xrontech.spring.ecom.dto.RegisterRequestDTO;
import com.xrontech.spring.ecom.model.User;
import com.xrontech.spring.ecom.repository.UserRepository;
import com.xrontech.spring.ecom.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> userSignUp(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.getFirstName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("First Name not Found");
        } else if (registerRequestDTO.getLastName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Last Name not Found");
        } else if (registerRequestDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not Found");
        } else if (!Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?^`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])*$").matcher(registerRequestDTO.getEmail().toLowerCase()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email Address");
        } else if (registerRequestDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile not Found");
        } else if (!Pattern.compile("^07[01245678][0-9]{7}$").matcher(registerRequestDTO.getMobile()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Mobile Number");
        } else if (registerRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not Found");
        }
//        else if (!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\*@_\\\\-\\\\#\\\\$\\\\%\\\\^&\\\\(\\\\)\\\\{\\\\}\\\\[\\\\]|])[8,20]$").matcher(userRegisterRequestDTO.getPassword()).matches()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your password must be 8-20 characters long and contain at least one digit, lowercase letter, uppercase letter, and special character from *, @, _, -, #, !, $, %, ^, &, (, ), {, }, [, ], or |.");
//        }
        else if (userRepository.findByEmail(registerRequestDTO.getEmail().toLowerCase()).isPresent()) {
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

    @Override
    public ResponseEntity<?> userLogIn(LogInRequestDTO logInRequestDTO) {
        if (logInRequestDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not Found");
        } else if (logInRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not Found");
        } else if (userRepository.findByEmailAndPassword(logInRequestDTO.getEmail(), logInRequestDTO.getPassword()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("User Logged Successfully!");
        }
    }
}
