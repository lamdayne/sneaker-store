package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateProductRequest;
import com.poly.sneakerstore.dto.request.UpdateProductRequest;
import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.dto.response.ProductResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse updateProduct(String id, UpdateProductRequest request);
    void deleteProduct(String id);
    ProductResponse findById(String id);
    PageResponse<?> findAll(int pageNo, int pageSize, String sortBy);
    PageResponse<?> search(Pageable pageable, String[] product, String[] variant);
}
