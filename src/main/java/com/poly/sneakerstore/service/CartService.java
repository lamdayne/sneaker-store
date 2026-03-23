package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CartRequest;
import com.poly.sneakerstore.dto.response.CartResponse;
import java.util.List;

public interface CartService {
    List<CartResponse> getMyCart(String userId);
    void addToCart(String userId, CartRequest request);
    void updateQuantity(Long cartItemId, int quantity);
    void removeFromCart(Long cartItemId);
    void clearCart(String userId);
}