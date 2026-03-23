package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductImageRequest {
    @NotBlank(message = "PRODUCT_ID_NOT_BLANK")
    private String productId;
    private String variantId;
    @NotBlank(message = "IMAGE_URL_NOT_BLANK")
    private String imageUrl;
    private Boolean isPrimary;
    private Integer displayOrder;
}