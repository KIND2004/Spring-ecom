package com.xrontech.spring.ecom.domain.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductResource {
    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductDTO addProductDTO) {
        return productService.addProduct(addProductDTO);
    }
}