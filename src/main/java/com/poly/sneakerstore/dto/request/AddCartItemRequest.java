package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequest {

    @NotBlank(message = "CART_ID_NOT_BLANK")
    private String cartId;

    @NotBlank(message = "VARIANT_ID_NOT_BLANK")
    private String variantId;

    @Min(value = 1, message = "QUANTITY_INVALID")
    private int quantity;
}