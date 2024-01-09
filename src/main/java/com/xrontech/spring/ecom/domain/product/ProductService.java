package com.xrontech.spring.ecom.domain.product;

import com.xrontech.spring.ecom.domain.brand.Brand;
import com.xrontech.spring.ecom.domain.brand.BrandRepository;
import com.xrontech.spring.ecom.domain.category.CategoryRepository;
import com.xrontech.spring.ecom.domain.product.brand_has_category.BrandHasCategory;
import com.xrontech.spring.ecom.domain.product.brand_has_category.BrandHasCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final BrandHasCategoryRepository brandHasCategoryRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<?> addProduct(AddProductDTO addProductDTO) {
        if (brandRepository.findById(addProductDTO.getBrandId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Brand not found");
        } else if (categoryRepository.findById(addProductDTO.getCategoryId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found");
        } else if (brandHasCategoryRepository.findByBrandIdAndCategoryId(addProductDTO.getBrandId(), addProductDTO.getCategoryId()).isEmpty()) {
            return ResponseEntity.badRequest().body("Brand has category not found");
        } else {
            BrandHasCategory brandHasCategory = brandHasCategoryRepository.findByBrandIdAndCategoryId(addProductDTO.getBrandId(), addProductDTO.getCategoryId()).get();
            if (productRepository.findByTitleAndBrandHasCategoryIdAndUserId(addProductDTO.getTitle(), brandHasCategory.getId(), 1L).isPresent()) {
                return ResponseEntity.badRequest().body("Product already exists");
            } else {
                productRepository.save(Product.builder()
                        .title(addProductDTO.getTitle())
                        .brandHasCategoryId(brandHasCategory.getId())
                        .price(addProductDTO.getPrice())
                        .quantity(addProductDTO.getQuantity())
                        .description(addProductDTO.getDescription())
                        .userId(1L)
                        .status(true)
                        .build());
                return ResponseEntity.ok().body("Product added successfully");
            }
        }
    }
}
