package com.poly.sneakerstore.service.impl;


import com.poly.sneakerstore.dto.request.CreateOrderItemRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderItemRequest;
import com.poly.sneakerstore.dto.response.OrderItemResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.OrderItemMapper;
import com.poly.sneakerstore.model.*;
import com.poly.sneakerstore.repository.*;
import com.poly.sneakerstore.service.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;
    private final UserRepository userRepository;
    private final ProductVariantRepository productVariantRepository;

    @Override
    @Transactional
    public OrderItemResponse create(CreateOrderItemRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        ProductVariant productVariant = productVariantRepository
                .findByProductIdAndColor(product.getId(), request.getColor())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND));

        if (productVariant.getStockQuantity() < request.getQuantity()) {
            throw new AppException(ErrorCode.OUT_OF_STOCK);
        }

        productVariant.setStockQuantity(productVariant.getStockQuantity() - request.getQuantity());
        productVariantRepository.save(productVariant);

        OrderItem orderItem = orderItemMapper.toOrderItem(request);
        orderItem.setOrder(order);
        orderItem.setProduct(product);

        orderItem.setTotalPrice(orderItem.getUnitPrice() * request.getQuantity());

        return orderItemMapper.toResponse(orderItemRepository.save(orderItem));
    }

    @Override
    public List<OrderItemResponse> getByOrder(String orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }

        return orderItemMapper.toResponseList(
                orderItemRepository.findByOrderId(orderId)
        );
    }

    @Override
    public OrderItemResponse update(String id, UpdateOrderItemRequest request) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));

        orderItemMapper.updateOrderItem(orderItem, request);
        return orderItemMapper.toResponse(orderItemRepository.save(orderItem));
    }


    @Override
    public void delete(String id) {
        if (!orderItemRepository.existsById(id)) {
            throw new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND);
        }

        orderItemRepository.deleteById(id);
    }

    @Override
    public List<OrderItemResponse> getAll() {
        return orderItemMapper.toResponseList(orderItemRepository.findAll());
    }

    @Override
    public OrderItemResponse getById(String id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND));
        return orderItemMapper.toResponse(orderItem);
    }
}
