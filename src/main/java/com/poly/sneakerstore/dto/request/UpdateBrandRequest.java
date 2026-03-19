package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBrandRequest {
    @NotBlank(message = "BRAND_NAME_NOT_BLANK")
    private String name;

    @NotBlank(message = "BRAND_LOGO_NOT_BLANK")
    private String logoUrl;

    @NotBlank(message = "BRAND_DESC_NOT_BLANK")
    private String description;

    @NotNull(message = "ACTIVE_NOT_NULL")
    private Boolean active;
}
