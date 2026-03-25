package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.CartItemResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.CartItemMapper;
import com.poly.sneakerstore.model.Cart;
import com.poly.sneakerstore.model.CartItem;
import com.poly.sneakerstore.model.ProductVariant;
import com.poly.sneakerstore.repository.CartItemRepository;
import com.poly.sneakerstore.repository.CartRepository;
import com.poly.sneakerstore.repository.ProductVariantRepository;
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
    private final CartRepository cartRepository;
    private final ProductVariantRepository variantRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    @Transactional
    public CartItemResponse add(AddCartItemRequest request) {
        Cart cart = cartRepository.findById(request.getCartId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        ProductVariant productVariant = variantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));

        CartItem cartItem = cartItemMapper.toCartItem(request);
        cartItem.setCart(cart);
        cartItem.setVariant(productVariant);
        cartItem.setAddedAt(LocalDateTime.now());
        return cartItemMapper.toResponse(cartItemRepository.save(cartItem));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CartItemResponse> getByUser(String userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        return cartItemRepository.findByCartId(cart.getId())
                .stream()
                .map(cartItemMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CartItemResponse update(String itemId, int quantity) {

        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        item.setQuantity(quantity);

        return cartItemMapper.toResponse(cartItemRepository.save(item));
    }

    @Override
    @Transactional
    public void delete(String itemId) {

        if (!cartItemRepository.existsById(itemId)) {
            throw new AppException(ErrorCode.CART_NOT_FOUND);
        }

        cartItemRepository.deleteById(itemId);
    }

    @Override
    @Transactional
    public void clear(String userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        List<CartItem> items = cartItemRepository.findByCartId(cart.getId());

        cartItemRepository.deleteAll(items);
    }
}