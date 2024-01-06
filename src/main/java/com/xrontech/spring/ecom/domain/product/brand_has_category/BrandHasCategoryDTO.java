package com.xrontech.spring.ecom.domain.product.brand_has_category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BrandHasCategoryDTO {
    private Long brandId;
    private Long categoryId;
}