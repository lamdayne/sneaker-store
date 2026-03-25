package com.poly.sneakerstore.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private String id;
    private String cartId;
    private String variantId;
    private int quantity;
    private double unitPrice;
    private LocalDateTime addedAt;
}