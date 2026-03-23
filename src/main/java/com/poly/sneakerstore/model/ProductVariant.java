package com.poly.sneakerstore.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_variants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String size;
    private String color;
    private String colorHex;
    private Integer stockQuantity;
    private Double priceOverride;

    @Column(name = "is_active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}