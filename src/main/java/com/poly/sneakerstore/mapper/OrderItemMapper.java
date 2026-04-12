package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateOrderItemRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderItemRequest;
import com.poly.sneakerstore.dto.response.OrderItemResponse;
import com.poly.sneakerstore.model.OrderItem;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderItemMapper {
    OrderItem toOrderItem(CreateOrderItemRequest request);

    @Mapping(source = "order.id", target = "orderId")
    OrderItemResponse toResponse(OrderItem item);

    List<OrderItemResponse> toResponseList(List<OrderItem> items);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderItem(@MappingTarget OrderItem item, UpdateOrderItemRequest request);
}
