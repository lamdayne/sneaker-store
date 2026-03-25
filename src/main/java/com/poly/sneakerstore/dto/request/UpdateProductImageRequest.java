package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.engine.spi.ManagedEntity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductImageRequest {
    @NotBlank(message = "IMAGE_URL_NOT_BLANK")
    private String imageUrl;

    @NotNull(message = "IS_PRIMARY_NOT_NULL")
    private Boolean isPrimary;

    @NotNull(message = "DISPLAY_ORDER_NOT_NULL")
    @Min(value = 0, message = "DISPLAY_ORDER_INVALID")
    private Integer displayOrder;
    @NotBlank(message = "VARIANT_ID_NOT_BLANK")
    private String variantId;
}