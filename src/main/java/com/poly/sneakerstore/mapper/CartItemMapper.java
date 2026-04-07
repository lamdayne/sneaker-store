package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.CartItemResponse;
import com.poly.sneakerstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CartItemMapper {
    CartItem toCartItem(AddCartItemRequest request);

    @Mapping(source = "user.id", target = "userId")
    CartItemResponse toCartItemResponse(CartItem cartItem);
}