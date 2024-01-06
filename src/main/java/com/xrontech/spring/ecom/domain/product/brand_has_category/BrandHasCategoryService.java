package com.xrontech.spring.ecom.domain.product.brand_has_category;

import com.xrontech.spring.ecom.domain.brand.Brand;
import com.xrontech.spring.ecom.domain.brand.BrandRepository;
import com.xrontech.spring.ecom.domain.category.Category;
import com.xrontech.spring.ecom.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandHasCategoryService {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final BrandHasCategoryRepository brandHasCategoryRepository;

    public ResponseEntity<?> addBrandHasCategory(@RequestBody BrandHasCategoryDTO brandHasCategoryDTO) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandHasCategoryDTO.getBrandId());
        Optional<Category> optionalCategory = categoryRepository.findById(brandHasCategoryDTO.getCategoryId());
        Optional<BrandHasCategory> optionalBrandHasCategory = brandHasCategoryRepository.findByBrandIdAndCategoryId(brandHasCategoryDTO.getBrandId(), brandHasCategoryDTO.getCategoryId());
        if (optionalBrand.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand not found");
        } else if (optionalCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        } else if (optionalBrandHasCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Brand has category already exist");
        } else {
            brandHasCategoryRepository.save(BrandHasCategory.builder()
                    .brandId(brandHasCategoryDTO.getBrandId())
                    .categoryId(brandHasCategoryDTO.getCategoryId())
                    .build());
            return ResponseEntity.status(HttpStatus.CREATED).body("Brand has category created");
        }
    }

    public ResponseEntity<?> loadBrandHasCategory() {
        List<BrandHasCategory> brandHasCategories = brandHasCategoryRepository.findAll();
        return ResponseEntity.ok(brandHasCategories);
    }
}