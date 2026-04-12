package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;
import com.poly.sneakerstore.model.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class, UserMapper.class})
public interface OrderMapper {
    Order toOrder(CreateOrderRequest request);

    @Mapping(source = "shippingAddress.id", target = "shippingAddressId")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderResponse toOrderResponse(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrder(UpdateOrderRequest request, @MappingTarget Order order);

    List<OrderResponse> toOrderResponse(List<Order> orders);
}
