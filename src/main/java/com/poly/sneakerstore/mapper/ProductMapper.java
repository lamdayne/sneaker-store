package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateProductRequest;
import com.poly.sneakerstore.dto.request.UpdateProductRequest;
import com.poly.sneakerstore.dto.response.ProductImageResponse;
import com.poly.sneakerstore.dto.response.ProductResponse;
import com.poly.sneakerstore.dto.response.ProductVariantResponse;
import com.poly.sneakerstore.model.Product;
import com.poly.sneakerstore.model.ProductImage;
import com.poly.sneakerstore.model.ProductVariant;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(CreateProductRequest request);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "brand.name", target = "brandName")
    @Mapping(target = "thumbnail", expression = "java(getThumbnail(product))")
    ProductResponse toProductResponse(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProduct(UpdateProductRequest request, @MappingTarget Product product);

    List<ProductResponse> toProductResponse(List<Product> products);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "variant.id", target = "variantId")
    ProductImageResponse toImageResponse(ProductImage image);

    @Mapping(source = "product.id", target = "productId")
    ProductVariantResponse toVariantResponse(ProductVariant variant);

    default String getThumbnail(Product product) {
        if (product.getProductImages() == null) return null;

        return product.getProductImages()
                .stream()
                .filter(ProductImage::isPrimary)
                .findFirst()
                .map(ProductImage::getImageUrl)
                .orElse(null);
    }
}
