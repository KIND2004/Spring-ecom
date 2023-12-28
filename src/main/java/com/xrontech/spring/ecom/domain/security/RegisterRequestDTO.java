package com.xrontech.spring.ecom.domain.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {
    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is Required")
    private String lastName;
    @Email
    @NotBlank(message = "Email is Required")
    private String email;
    @NotBlank(message = "Mobile is Required")
    @Pattern(regexp = "^07[01245678][0-9]{7}$")
    @Size(min = 10, max = 10)
    private String mobile;
    @NotBlank(message = "Password is Required")
    private String password;
}