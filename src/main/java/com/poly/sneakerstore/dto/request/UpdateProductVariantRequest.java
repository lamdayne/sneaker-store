package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductVariantRequest {
    @NotBlank(message = "SIZE_NOT_BLANK")
    private String size;

    @NotBlank(message = "COLOR_NOT_BLANK")
    private String color;

    private String colorHex;

    @Min(value = 0, message = "STOCK_INVALID")
    private Integer stockQuantity;

    private Double priceOverride;

    @NotNull(message = "ACTIVE_NOT_NULL")
    private Boolean active;
}