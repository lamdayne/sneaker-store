package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateProductImageRequest;
import com.poly.sneakerstore.dto.request.UpdateProductImageRequest;
import com.poly.sneakerstore.dto.response.ProductImageResponse;

import java.util.List;

public interface ProductImageService {
    ProductImageResponse addImage(CreateProductImageRequest request);
    ProductImageResponse updateImage(String imageId, UpdateProductImageRequest request);
    void deleteImage(String imageId);
    List<ProductImageResponse> getImagesByProductId(String productId);
}