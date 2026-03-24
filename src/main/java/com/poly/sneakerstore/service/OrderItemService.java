package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateOrderItemRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderItemRequest;
import com.poly.sneakerstore.dto.request.UpdateProductVariantRequest;
import com.poly.sneakerstore.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {

    OrderItemResponse create(CreateOrderItemRequest request);

    List<OrderItemResponse> getByOrder(String orderId);

    OrderItemResponse update(String id, UpdateOrderItemRequest request);

    void delete(String id);

    List<OrderItemResponse> getAll();

    OrderItemResponse getById(String id);

}
