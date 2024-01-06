package com.xrontech.spring.ecom.domain.product.brand_has_category;

import com.xrontech.spring.ecom.domain.brand.Brand;
import com.xrontech.spring.ecom.domain.category.Category;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "brand_has_category")
public class BrandHasCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Category category;
}
