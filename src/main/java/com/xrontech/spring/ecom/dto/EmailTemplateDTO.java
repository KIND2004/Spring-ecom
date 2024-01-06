package com.xrontech.spring.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailTemplateDTO {
    private String username;
    private String verificationCode;
}