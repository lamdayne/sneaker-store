package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import lombok.*;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CreateCartRequest {

    @NotBlank(message = "USER_ID_NOT_BLANK")
    private String userId;

    @Min(value = 1, message = "DISPLAY_ORDER")
    private Integer daysToLive;
}