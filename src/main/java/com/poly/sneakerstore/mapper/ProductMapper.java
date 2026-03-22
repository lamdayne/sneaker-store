package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateProductRequest;
import com.poly.sneakerstore.dto.request.UpdateProductRequest;
import com.poly.sneakerstore.dto.response.ProductResponse;
import com.poly.sneakerstore.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(CreateProductRequest request);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(UpdateProductRequest request, @MappingTarget Product product);

    List<ProductResponse> toProductResponse(List<Product> products);
}
