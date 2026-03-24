package com.poly.sneakerstore.service.impl;


import com.poly.sneakerstore.dto.request.CreateOrderItemRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderItemRequest;
import com.poly.sneakerstore.dto.response.OrderItemResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.OrderItemMapper;
import com.poly.sneakerstore.model.Order;
import com.poly.sneakerstore.model.OrderItem;
import com.poly.sneakerstore.model.Product;
import com.poly.sneakerstore.model.ProductVariant;
import com.poly.sneakerstore.repository.OrderItemRepository;
import com.poly.sneakerstore.repository.OrderRepository;
import com.poly.sneakerstore.repository.ProductVariantRepository;
import com.poly.sneakerstore.service.OrderItemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderItemResponse create(CreateOrderItemRequest request) {
       
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        ProductVariant variant = productVariantRepository.findById(request.getVariantId())
                .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));

        Product product = variant.getProduct();

        if (variant.getStockQuantity() < request.getQuantity()) {
            throw new AppException(ErrorCode.STOCK_INVALID);
        }

        Double price = variant.getPriceOverride() != null
                ? variant.getPriceOverride()
                : product.getBasePrice();

        if (price == null || price <= 0) {
            throw new AppException(ErrorCode.PRODUCT_PRICE_NOT_BLANK);
        }

        Double total = price * request.getQuantity();

        OrderItem item = OrderItem.builder()
                .order(order)
                .variant(variant)
                .productName(product.getName())
                .size(variant.getSize())
                .color(variant.getColor())
                .quantity(request.getQuantity())
                .unitPrice(price)
                .totalPrice(total)
                .build();

        variant.setStockQuantity(
                variant.getStockQuantity() - request.getQuantity()
        );

        OrderItem saved = orderItemRepository.save(item);

        return orderItemMapper.toResponse(saved);
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
