package com.xrontech.spring.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
}