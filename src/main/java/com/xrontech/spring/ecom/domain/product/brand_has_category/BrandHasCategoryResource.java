package com.xrontech.spring.ecom.domain.product.brand_has_category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand-has-category")
public class BrandHasCategoryResource {
    private final BrandHasCategoryService brandHasCategoryService;

    @PostMapping("create")
    public ResponseEntity<?> addBrandHasCategory(BrandHasCategoryDTO brandHasCategoryDTO) {
        return brandHasCategoryService.addBrandHasCategory(brandHasCategoryDTO);
    }

    @GetMapping("/load")
    public ResponseEntity<?> loadBrandHasCategory() {
        return brandHasCategoryService.loadBrandHasCategory();
    }
}
