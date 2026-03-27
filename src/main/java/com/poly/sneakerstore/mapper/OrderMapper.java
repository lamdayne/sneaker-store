package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;
import com.poly.sneakerstore.model.Order;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "shippingAddress.id", source = "shippingAddressId")
    Order toOrder(CreateOrderRequest request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "shippingAddress.id", target = "shippingAddressId")
    OrderResponse toOrderResponse(Order order);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrder(UpdateOrderRequest request, @MappingTarget Order order);

    List<OrderResponse> toOrderResponse(List<Order> orders);
}
