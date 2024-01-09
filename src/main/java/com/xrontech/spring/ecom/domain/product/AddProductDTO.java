package com.xrontech.spring.ecom.domain.product;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddProductDTO {
    @NotBlank(message = "Title is Required")
    private String title;
    private Long brandId;
    private Long categoryId;
    private Integer quantity;
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;
    @NotBlank(message = "Description is Required")
    private String description;
}
