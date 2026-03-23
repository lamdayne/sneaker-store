package com.poly.sneakerstore.dto.request;

import lombok.Data;

@Data
public class CartRequest {
    private String productVariantId;
    private int quantity;
}