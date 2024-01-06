package com.xrontech.spring.ecom.domain.product.brand_has_category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandHasCategoryRepository extends JpaRepository<BrandHasCategory, Long> {
    Optional<BrandHasCategory> findByBrandIdAndCategoryId(Long brandId, Long categoryId);
}
