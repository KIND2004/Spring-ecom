package com.xrontech.spring.ecom.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateProfileDTO {
    @NotBlank(message = "First Name is Required")
    private String firstName;
    @NotBlank(message = "Last Name is Required")
    private String lastName;
    @NotBlank(message = "Mobile is Required")
    @Pattern(regexp = "^07[01245678][0-9]{7}$")
    private String mobile;
    private String addressNumber;
    private String streetName;
    private String city;
    @Size(min = 5, max = 5)
    private String zipCode;
}
