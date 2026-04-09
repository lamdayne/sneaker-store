package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;
import com.poly.sneakerstore.dto.response.PageResponse;
import vn.payos.model.webhooks.WebhookData;

import java.util.List;

public interface OrderService {
     OrderResponse createOrder(CreateOrderRequest request);
     OrderResponse updateOrder(String id, UpdateOrderRequest request);
     void deleteOrder(String id);
     OrderResponse getOrderById(String id);
     PageResponse<?> getAllOrders(int pageNo, int pageSize, String sortBy);
     List<OrderResponse> getAllMyOrders();
     void updatePaymentStatus(WebhookData webhook, String orderCode);
     String getPaymentStatusByOrderCode(String orderCode);
     void changeStatus(String orderCode, String status);
     void cancelOrder(String orderCode);
}