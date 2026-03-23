package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductVariantRequest {
    @NotBlank(message = "PRODUCT_ID_NOT_BLANK")
    private String productId;

    @NotBlank(message = "SIZE_NOT_BLANK")
    private String size;

    @NotBlank(message = "COLOR_NOT_BLANK")
    private String color;

    private String colorHex;

    @Min(value = 0, message = "STOCK_INVALID")
    private Integer stockQuantity;

    private Double priceOverride;
}

