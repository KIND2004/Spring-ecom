package com.xrontech.spring.ecom.domain.product;

import com.xrontech.spring.ecom.domain.product.brand_has_category.BrandHasCategory;
import com.xrontech.spring.ecom.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "brand_has_category_id")
    private Long brandHasCategoryId;
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "status")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "brand_has_category_id", referencedColumnName = "id", insertable = false, updatable = false)
    private BrandHasCategory brandHasCategory;
}