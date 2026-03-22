package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
    @NotBlank(message = "PRODUCT_NAME_NOT_BLANK")
    private String name;

    @NotBlank(message = "PRODUCT_DESC_NOT_BLANK")
    private String description;

    @NotNull(message = "PRODUCT_PRICE_NOT_BLANK")
    private Double basePrice;

    @NotBlank(message = "PRODUCT_MATERIAL_NOT_BLANK")
    private String material;

    @NotNull(message = "FEATURED_NOT_NULL")
    private Boolean featured;

    @NotBlank(message = "BRAND_ID_NOT_BLANK")
    private String brandId;

    @NotBlank(message = "CATEGORY_ID_NOT_BLANK")
    private String categoryId;

    @NotNull(message = "ACTIVE_NOT_NULL")
    private Boolean active;
}
