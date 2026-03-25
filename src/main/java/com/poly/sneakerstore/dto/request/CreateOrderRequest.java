package com.poly.sneakerstore.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.util.List;
@Getter
@Setter

public class CreateOrderRequest {
    @NotBlank(message = "USER_ID_NOT_BLANK")
    private String userId;

    @NotBlank(message = "ADDRESS_ID_NOT_BLANK")
    private String shippingAddressId;

    @NotBlank(message = "ORDER_CODE_NOT_BLANK")
    private String orderCode;

    @NotNull(message = "SUBTOTAL_NOT_NULL")
    private Double subtotal;

    @NotNull(message = "TOTAL_AMOUNT_NOT_NULL")
    private Double totalAmount;
    private Double shippingFee;
    private Double discountAmount;
    private String paymentMethod;
    private String note;
}
