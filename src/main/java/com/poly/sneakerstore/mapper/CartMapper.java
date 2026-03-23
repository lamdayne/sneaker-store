package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.response.CartResponse;
import com.poly.sneakerstore.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "productVariant.product.name", target = "productName")
    @Mapping(source = "productVariant.size.name", target = "size")
    @Mapping(source = "productVariant.color.name", target = "color")
    @Mapping(source = "productVariant.price", target = "price")
    @Mapping(source = "productVariant.product.avatarUrl", target = "imageUrl")
    CartResponse toCartResponse(Cart cart);
}