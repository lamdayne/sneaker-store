package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.CartItemResponse;

import java.util.List;

public interface CartItemService {
    CartItemResponse addCartItem(AddCartItemRequest request);
    void deleteCartItem(String cartItemId);
    List<CartItemResponse> getMyCartItem();
}