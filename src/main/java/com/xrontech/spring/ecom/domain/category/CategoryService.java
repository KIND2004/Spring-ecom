package com.xrontech.spring.ecom.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> createCategory(String categoryName) {
        if (categoryRepository.findByName(categoryName).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exists");
        } else {
            categoryRepository.save(new Category.CategoryBuilder().name(categoryName).build());
            return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");
        }
    }

    public ResponseEntity<?> loadCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }
}
