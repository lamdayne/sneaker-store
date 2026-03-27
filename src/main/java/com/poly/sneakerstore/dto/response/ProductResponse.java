package com.poly.sneakerstore.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Double basePrice;
    private String material;
    private Boolean featured;
    private Boolean active;
    private String brandId;
    private String brandName;
    private String categoryId;
    private String thumbnail;
    private List<ProductImageResponse> productImages;
    private List<ProductVariantResponse> productVariants;
    private LocalDateTime createdAt;
}
