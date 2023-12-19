package com.xrontech.spring.ecom.service.serviceImpl;

import com.xrontech.spring.ecom.dto.UserLogInRequestDTO;
import com.xrontech.spring.ecom.dto.UserRegisterRequestDTO;
import com.xrontech.spring.ecom.model.User;
import com.xrontech.spring.ecom.repository.UserRepository;
import com.xrontech.spring.ecom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?> userSignUp(UserRegisterRequestDTO userRegisterRequestDTO) {
        if (userRegisterRequestDTO.getFirstName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("First Name not Found");
        } else if (userRegisterRequestDTO.getLastName().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Last Name not Found");
        } else if (userRegisterRequestDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not Found");
        } else if (!Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?^`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])*$").matcher(userRegisterRequestDTO.getEmail().toLowerCase()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email Address");
        } else if (userRegisterRequestDTO.getMobile().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mobile not Found");
        } else if (!Pattern.compile("^07[01245678][0-9]{7}$").matcher(userRegisterRequestDTO.getMobile()).matches()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Mobile Number");
        } else if (userRegisterRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not Found");
        }
//        else if (!Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\\\*@_\\\\-\\\\#\\\\$\\\\%\\\\^&\\\\(\\\\)\\\\{\\\\}\\\\[\\\\]|])[8,20]$").matcher(userRegisterRequestDTO.getPassword()).matches()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Your password must be 8-20 characters long and contain at least one digit, lowercase letter, uppercase letter, and special character from *, @, _, -, #, !, $, %, ^, &, (, ), {, }, [, ], or |.");
//        }
        else if (userRepository.findByEmail(userRegisterRequestDTO.getEmail().toLowerCase()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Exist");
        } else if (userRepository.findByMobile(userRegisterRequestDTO.getMobile()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile Already Exist");
        } else {
            User user = new User();
            user.setFirstName(userRegisterRequestDTO.getFirstName());
            user.setLastName(userRegisterRequestDTO.getLastName());
            user.setEmail(userRegisterRequestDTO.getEmail().toLowerCase());
            user.setMobile(userRegisterRequestDTO.getMobile());
            user.setPassword(userRegisterRequestDTO.getPassword());

            userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully!");
        }
    }

    @Override
    public ResponseEntity<?> userLogIn(UserLogInRequestDTO userLogInRequestDTO) {
        if (userLogInRequestDTO.getEmail().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not Found");
        } else if (userLogInRequestDTO.getPassword().equals("")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Password not Found");
        } else if (userRepository.findByEmailAndPassword(userLogInRequestDTO.getEmail(), userLogInRequestDTO.getPassword()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("User Logged Successfully!");
        }
    }
}
