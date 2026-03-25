package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.CartItemResponse;
import com.poly.sneakerstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(AddCartItemRequest request);
    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "variant.id", target = "variantId")
    CartItemResponse toResponse(CartItem cartItem);
}