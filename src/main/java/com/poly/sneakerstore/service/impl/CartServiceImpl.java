package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CartRequest;
import com.poly.sneakerstore.dto.response.CartResponse;
import com.poly.sneakerstore.mapper.CartMapper;
import com.poly.sneakerstore.model.*;
import com.poly.sneakerstore.repository.*;
import com.poly.sneakerstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductVariantRepository variantRepository;
    private final CartMapper cartMapper;

    @Override
    public List<CartResponse> getMyCart(String userId) {
        return cartRepository.findByUserId(userId).stream()
                .map(cartMapper::toCartResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addToCart(String userId, CartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProductVariant variant = variantRepository.findById(request.getProductVariantId())
                .orElseThrow(() -> new RuntimeException("Product variant not found"));

        cartRepository.findByUserIdAndProductVariantId(userId, request.getProductVariantId())
                .ifPresentOrElse(
                        existingCart -> {
                            existingCart.setQuantity(existingCart.getQuantity() + request.getQuantity());
                            cartRepository.save(existingCart);
                        },
                        () -> cartRepository.save(Cart.builder()
                                .user(user)
                                .productVariant(variant)
                                .quantity(request.getQuantity())
                                .build())
                );
    }

    @Override
    @Transactional
    public void updateQuantity(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        if (quantity <= 0) {
            cartRepository.delete(cart);
        } else {
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    @Override
    @Transactional
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    @Transactional
    public void clearCart(String userId) {
        List<Cart> items = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(items);
    }
}