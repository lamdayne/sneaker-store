package com.poly.sneakerstore.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemResponse {

    private String id;
    private String orderId;
    private String variantId;
    private String productName;
    private String size;
    private String color;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;

}
