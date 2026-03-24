package com.poly.sneakerstore.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderItemRequest {

    @NotBlank(message = "PRODUCT_NAME_NOT_BLANK")
    private String productName;

    @NotBlank(message = "SIZE_NOT_BLANK")
    private String size;

    @NotBlank(message = "COLOR_NOT_BLANK")
    private String color;

    @NotNull(message = "QUANTITY_NOT_NULL")
    @Min(value = 1, message = "QUANTITY_INVALID")
    private Integer quantity;

    @NotNull(message = "UNIT_PRICE_NOT_NULL")
    @Min(value = 1, message = "UNIT_PRICE_INVALID")
    private Double unitPrice;

    @NotNull(message = "TOTAL_PRICE_NOT_NULL")
    @Min(value = 1, message = "TOTAL_PRICE_INVALID")
    private Double totalPrice;
}
