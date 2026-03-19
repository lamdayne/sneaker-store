package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class CreateCategoryRequest {
    @NotBlank(message = "CATEGORY_NAME_NOT_BLANK")
    private String name;

    @NotBlank(message = "CATEGORY_IMG_NOT_BLANK")
    private String imageUrl;

    @Min(value = 1, message = "DISPLAY_ORDER")
    private Integer displayOrder;

    @NotNull(message = "ACTIVE_NOT_NULL")
    private Boolean active;
}
