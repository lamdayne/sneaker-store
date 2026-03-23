package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderRequest {
    @NotBlank(message = "ORDER_ID_NOT_BLANK")
    private String id;

    private String shippingAddressId;

    private String paymentMethod;

    private String status;

    private String paymentStatus;

    private String note;
}
