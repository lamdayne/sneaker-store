package com.poly.sneakerstore.service;

import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.model.Address;
import com.poly.sneakerstore.model.CartItem;
import com.poly.sneakerstore.model.Order;
import com.poly.sneakerstore.repository.AddressRepository;
import com.poly.sneakerstore.repository.CartItemRepository;
import com.poly.sneakerstore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final CartItemRepository cartItemRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;

    public boolean isCartItemOwner(String cartItemId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        return cartItem.getUser().getEmail().equals(email);
    }

    public boolean isAddressOwner(String addressId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        return address.getUser().getEmail().equals(email);
    }

    public boolean isOrderOwner(String orderCode) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return order.getUser().getEmail().equals(email);
    }
}
