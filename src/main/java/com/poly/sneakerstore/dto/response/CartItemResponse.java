package com.poly.sneakerstore.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private String id;
    private String userId;
    private CartProductResponse product;
    private String color;
    private String size;
    private int quantity;
    private double unitPrice;
}