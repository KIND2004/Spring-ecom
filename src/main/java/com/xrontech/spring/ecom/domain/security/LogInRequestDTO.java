package com.xrontech.spring.ecom.domain.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInRequestDTO {
    @Email
    @NotBlank(message = "Email is Required")
    private String email;
    @NotBlank(message = "Password is Required")
    private String password;
}
