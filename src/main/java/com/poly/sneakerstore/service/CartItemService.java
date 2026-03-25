package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.CartItemResponse;
import com.poly.sneakerstore.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItemResponse add(AddCartItemRequest request);

    List<CartItemResponse> getByUser(String userId);

    CartItemResponse update(String itemId, int quantity);

    void delete(String itemId);

    void clear(String userId);
}