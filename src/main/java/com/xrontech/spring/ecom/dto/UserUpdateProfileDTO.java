package com.xrontech.spring.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateProfileDTO {
    private String firstName;
    private String lastName;
    private String mobile;
//    private String profileImagePath;
    private String addressNumber;
    private String streetName;
    private String city;
    private String zipCode;
}
