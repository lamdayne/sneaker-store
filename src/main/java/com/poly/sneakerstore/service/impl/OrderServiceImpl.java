package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;
import com.poly.sneakerstore.enums.OrderStatus;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.OrderMapper;
import com.poly.sneakerstore.model.Address;
import com.poly.sneakerstore.model.Order;
import com.poly.sneakerstore.model.User;
import com.poly.sneakerstore.repository.AddressRepository;
import com.poly.sneakerstore.repository.OrderRepository;
import com.poly.sneakerstore.repository.UserRepository;
import com.poly.sneakerstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Address address = addressRepository.findByIdAndUserId(request.getShippingAddressId(), user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        Order order = orderMapper.toOrder(request);
        order.setOrderCode("ORD-" + UUID.randomUUID().toString().substring(0,8));
        order.setStatus(OrderStatus.PENDING.name());
        order.setPaymentStatus("UNPAID");
        order.setShippingAddress(address);
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());
        order = orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse updateOrder(String id, UpdateOrderRequest request) {
        Address address = addressRepository.findById(request.getShippingAddressId())
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ORDER_NOT_FOUND"));

        orderMapper.updateOrder(request, order);
        order.setShippingAddress(address);
        order = orderRepository.save(order);

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.CANCELLED.name());
        orderRepository.save(order);
    }

    @Override
    public OrderResponse getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toOrderResponse(orders);
    }
}
