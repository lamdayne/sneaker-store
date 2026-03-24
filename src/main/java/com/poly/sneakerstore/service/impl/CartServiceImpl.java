package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateCartRequest;
import com.poly.sneakerstore.dto.response.CartResponse;
import com.poly.sneakerstore.mapper.CartMapper;
import com.poly.sneakerstore.model.Cart;
import com.poly.sneakerstore.model.User;
import com.poly.sneakerstore.repository.CartRepository;
import com.poly.sneakerstore.repository.UserRepository;
import com.poly.sneakerstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartResponse createCart(CreateCartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(Cart.builder()
                        .user(user)
                        .expiresAt(LocalDateTime.now().plusDays(request.getDaysToLive() != null ? request.getDaysToLive() : 7))
                        .build()));

        return cartMapper.toResponse(cart);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponse getCartByUserId(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
        return cartMapper.toResponse(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(String id, Integer extraDays) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.setExpiresAt(LocalDateTime.now().plusDays(extraDays != null ? extraDays : 7));
        return cartMapper.toResponse(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public void deleteCart(String id) {
        if (!cartRepository.existsById(id)) throw new RuntimeException("Cart not found");
        cartRepository.deleteById(id);
    }
}