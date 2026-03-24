package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateCartRequest;
import com.poly.sneakerstore.dto.response.CartResponse;

public interface CartService {
    CartResponse createCart(CreateCartRequest request);
    CartResponse getCartByUserId(String userId);
    CartResponse updateCart(String id, Integer extraDays);
    void deleteCart(String id);
}