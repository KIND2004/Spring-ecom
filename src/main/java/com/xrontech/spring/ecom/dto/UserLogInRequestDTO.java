package com.xrontech.spring.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLogInRequestDTO {
    private String email;
    private String password;
}
