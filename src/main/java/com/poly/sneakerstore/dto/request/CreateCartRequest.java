package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartRequest {

    @NotBlank(message = "USER_ID_NOT_BLANK")
    private String userId;
}