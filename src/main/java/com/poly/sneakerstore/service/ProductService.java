package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateProductRequest;
import com.poly.sneakerstore.dto.request.UpdateProductRequest;
import com.poly.sneakerstore.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse updateProduct(String id, UpdateProductRequest request);
    void deleteProduct(String id);
    ProductResponse findById(String id);
    List<ProductResponse> findAll();
}
