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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        CartItem cartItem = cartItemMapper.toCartItem(request);
        cartItem.setProduct(product);
        cartItem.setUser(user);
        return cartItemMapper.toCartItemResponse(cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteCartItem(String cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
        cartItemRepository.delete(cartItem);
    }
}