package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateProductImageRequest;
import com.poly.sneakerstore.dto.response.ProductImageResponse;
import com.poly.sneakerstore.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "variant.id", target = "variantId")
    @Mapping(source = "primary", target = "isPrimary")
    ProductImageResponse toResponse(ProductImage productImage);

    List<ProductImageResponse> toResponseList(List<ProductImage> productImages);
}