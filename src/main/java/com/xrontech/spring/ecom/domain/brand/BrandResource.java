package com.xrontech.spring.ecom.domain.brand;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brand")
public class BrandResource {

    private final BrandService brandService;
    @PostMapping("/create-brand")
    public ResponseEntity<?> createBrand(@Valid @RequestBody @NotBlank(message = "Brand Name is Required") String brandName) {
        return brandService.createBrand(brandName);
    }

    @GetMapping("/load-brands")
    public ResponseEntity<?> loadBrand() {
        return brandService.loadBrands();
    }
}