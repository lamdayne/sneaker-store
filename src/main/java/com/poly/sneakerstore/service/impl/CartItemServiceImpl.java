package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.CartItemResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.CartItemMapper;
import com.poly.sneakerstore.model.*;
import com.poly.sneakerstore.repository.*;
import com.poly.sneakerstore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public CartItemResponse addCartItem(AddCartItemRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        CartItem oldCartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId());

        if (oldCartItem != null && oldCartItem.getSize().equals(request.getSize())) {
            oldCartItem.setQuantity(oldCartItem.getQuantity() + request.getQuantity());
            return cartItemMapper.toCartItemResponse(cartItemRepository.save(oldCartItem));
        }

        CartItem cartItem = cartItemMapper.toCartItem(request);
        cartItem.setProduct(product);
        cartItem.setUser(user);
        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
    }

    @Override
    @PreAuthorize("@securityService.isCartItemOwner(#cartItemId)")
    public void deleteCartItem(String cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItemResponse> getMyCartItem() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return cartItemRepository.findByUserId(user.getId())
                .stream()
                .map(cartItemMapper::toCartItemResponse)
                .toList();
    }

    @Override
    public void clearMyCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        cartItemRepository.deleteAllByUserId(user.getId());
    }
}