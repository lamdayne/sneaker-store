package com.poly.sneakerstore.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantResponse {
    private String id;
    private String productId;
    private String size;
    private String color;
    private String colorHex;
    private Integer stockQuantity;
    private Double priceOverride;
    private boolean active;
}