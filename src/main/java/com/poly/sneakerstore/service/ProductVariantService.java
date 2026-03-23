package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateProductVariantRequest;
import com.poly.sneakerstore.dto.request.UpdateProductVariantRequest;
import com.poly.sneakerstore.dto.response.ProductVariantResponse;
import java.util.List;

public interface ProductVariantService {
    ProductVariantResponse createVariant(CreateProductVariantRequest request);
    List<ProductVariantResponse> getByProductId(String productId);
    void deleteVariant(String variantId);
    ProductVariantResponse updateVariant(String variantId, UpdateProductVariantRequest request);
    ProductVariantResponse getVariantById(String variantId);
    List<ProductVariantResponse> getAllVariants();
}
