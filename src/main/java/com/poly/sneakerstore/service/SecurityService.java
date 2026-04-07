package com.poly.sneakerstore.service;

import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.model.CartItem;
import com.poly.sneakerstore.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final CartItemRepository cartItemRepository;

    public boolean isCartItemOwner(String cartItemId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        return cartItem.getUser().getEmail().equals(email);
    }
}
