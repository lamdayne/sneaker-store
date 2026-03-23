package com.poly.sneakerstore.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageResponse {
    private String id;
    private String imageUrl;
    private boolean isPrimary;
    private Integer displayOrder;
    private String productId;
    private String variantId;
}