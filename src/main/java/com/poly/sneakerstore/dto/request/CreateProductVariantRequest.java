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
public class CreateProductVariantRequest {
    @NotBlank(message = "PRODUCT_ID_NOT_BLANK")
    private String productId;

    @NotBlank(message = "SIZE_NOT_BLANK")
    private String size;

    @NotBlank(message = "COLOR_NOT_BLANK")
    private String color;

    @NotBlank(message = "COLOR_HEX_NOT_BLANK")
    private String colorHex;

    @NotNull(message = "STOCK_NOT_NULL")
    @Min(value = 0, message = "STOCK_INVALID")
    private Integer stockQuantity;

    @NotNull(message = "PRICE_NOT_NULL")
    @Min(value = 0, message = "PRICE_INVALID")
    private Double priceOverride;
}