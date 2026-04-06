package com.poly.sneakerstore.dto.response;

import com.poly.sneakerstore.model.Cart;
import com.poly.sneakerstore.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {
    private String id;
    private String userId;
    private String productId;
    private String color;
    private String size;
    private int quantity;
    private double unitPrice;
}