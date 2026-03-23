package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateProductVariantRequest;
import com.poly.sneakerstore.dto.response.ProductVariantResponse;
import com.poly.sneakerstore.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariant toEntity(CreateProductVariantRequest request);

    @Mapping(source = "product.id", target = "productId")
    ProductVariantResponse toResponse(ProductVariant variant);

    List<ProductVariantResponse> toListResponse(List<ProductVariant> variants);
}