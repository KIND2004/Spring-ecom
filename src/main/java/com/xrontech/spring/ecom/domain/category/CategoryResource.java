package com.xrontech.spring.ecom.domain.category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryResource {
    private final CategoryService categoryService;

    @PostMapping("/create-category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody @NotBlank(message = "Category Name is Required") String categoryName) {
        return categoryService.createCategory(categoryName);
    }

    @GetMapping("/load-categories")
    public ResponseEntity<?> loadCategories() {
        return categoryService.loadCategories();
    }
}
