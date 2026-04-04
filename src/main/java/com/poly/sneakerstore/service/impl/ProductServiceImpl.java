package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateProductRequest;
import com.poly.sneakerstore.dto.request.UpdateProductRequest;
import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.dto.response.ProductResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.ProductMapper;
import com.poly.sneakerstore.model.Brand;
import com.poly.sneakerstore.model.Category;
import com.poly.sneakerstore.model.Product;
import com.poly.sneakerstore.repository.BrandRepository;
import com.poly.sneakerstore.repository.CategoryRepository;
import com.poly.sneakerstore.repository.ProductRepository;
import com.poly.sneakerstore.service.ProductService;
import com.poly.sneakerstore.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final PageableUtil pageableUtil;

    @Override
    public ProductResponse createProduct(CreateProductRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = productMapper.toProduct(request);
        product.setBrand(brand);
        product.setCategory(category);
        product.setActive(true);
        product.setCreatedAt(LocalDateTime.now());

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(String id, UpdateProductRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productMapper.updateProduct(request, product);
        product.setBrand(brand);
        product.setCategory(category);

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public ProductResponse findById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toProductResponse(product);
    }

    @Override
    public PageResponse<?> findAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = pageableUtil.createPageable(pageNo, pageSize, sortBy);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> response = products.stream().map(productMapper::toProductResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(products.getTotalPages())
                .totalElements((int) products.getTotalElements())
                .items(response)
                .build();
    }
}
