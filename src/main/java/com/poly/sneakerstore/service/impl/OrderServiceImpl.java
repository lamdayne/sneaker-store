package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;
import com.poly.sneakerstore.dto.response.PageResponse;
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
import com.poly.sneakerstore.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.payos.model.webhooks.WebhookData;

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
    private final PageableUtil pageableUtil;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Address address = addressRepository.findByIdAndUserId(request.getShippingAddressId(), user.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        Order order = orderMapper.toOrder(request);
        order.setOrderCode("ORD" + UUID.randomUUID().toString().substring(0,8));
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
    @PreAuthorize("hasRole('ADMIN')")
    public PageResponse<?> getAllOrders(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = pageableUtil.createPageable(pageNo, pageSize, sortBy);
        Page<Order> page = orderRepository.findAll(pageable);
        List<OrderResponse> response = page.stream().map(orderMapper::toOrderResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .totalElements((int) page.getTotalElements())
                .items(response)
                .build();
    }

    @Override
    public List<OrderResponse> getAllMyOrders() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        List<Order> orders = orderRepository.findAllByUserId(user.getId());
        return orderMapper.toOrderResponse(orders);
    }

    @Override
    public void updatePaymentStatus(WebhookData webhook, String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setPaymentStatus("PAID");
        orderRepository.save(order);
    }

    @Override
    public String getPaymentStatusByOrderCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return order.getPaymentStatus();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void changeStatus(String orderCode, String status) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    @PreAuthorize("@securityService.isOrderOwner(#orderCode)")
    public void cancelOrder(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.CANCELLED.name());
        orderRepository.save(order);
    }


}
