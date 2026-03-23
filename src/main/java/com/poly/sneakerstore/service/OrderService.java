package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
     OrderResponse createOrder(CreateOrderRequest request);
     OrderResponse updateOrder(String id, UpdateOrderRequest request);
     void deleteOrder(String id);
     OrderResponse getOrderById(String id);
     List<OrderResponse> getAllOrders();
}