package com.xrontech.spring.ecom.domain.brand;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public ResponseEntity<?> createBrand(String brandName) {
        if (brandRepository.findByName(brandName).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Brand already exists");
        } else {
            brandRepository.save(new Brand.BrandBuilder().name(brandName).build());
            return ResponseEntity.status(HttpStatus.CREATED).body("Brand created successfully");
        }
    }

    public ResponseEntity<?> loadBrands() {
        List<Brand> brands = brandRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(brands);
    }
}