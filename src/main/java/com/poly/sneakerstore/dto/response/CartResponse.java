package com.poly.sneakerstore.dto.response;

import lombok.*;

@Data
@Builder
public class CartResponse {
    private Long id;
    private String productName;
    private String size;
    private String color;
    private double price;
    private int quantity;
    private String imageUrl;
}