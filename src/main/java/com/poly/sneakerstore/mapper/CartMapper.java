package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.response.CartResponse;
import com.poly.sneakerstore.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userFullName")
    CartResponse toResponse(Cart cart);
}