package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductImageRequest {
    @NotBlank(message = "IMAGE_URL_NOT_BLANK")
    private String imageUrl;

    private Boolean isPrimary;
    private Integer displayOrder;
    private String variantId;
}