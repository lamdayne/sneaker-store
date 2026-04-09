package com.poly.sneakerstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private String id;
    private String orderCode;
    private String userId;
    private String shippingAddressId;

    private Double subtotal;
    private Double shippingFee;
    private Double discountAmount;
    private Double totalAmount;

    private String status;
    private String paymentMethod;
    private String paymentStatus;
    private UserResponse user;
    private String note;
    private List<OrderItemResponse> orderItems;
    private LocalDateTime createdAt;
}
