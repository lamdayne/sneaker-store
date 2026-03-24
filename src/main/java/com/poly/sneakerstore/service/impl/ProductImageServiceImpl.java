package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateProductImageRequest;
import com.poly.sneakerstore.dto.request.UpdateProductImageRequest;
import com.poly.sneakerstore.dto.response.ProductImageResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.ProductImageMapper;
import com.poly.sneakerstore.model.Product;
import com.poly.sneakerstore.model.ProductImage;
import com.poly.sneakerstore.model.ProductVariant;
import com.poly.sneakerstore.repository.ProductImageRepository;
import com.poly.sneakerstore.repository.ProductRepository;
import com.poly.sneakerstore.repository.ProductVariantRepository;
import com.poly.sneakerstore.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ProductVariantRepository variantRepository;
    private final ProductImageMapper imageMapper;

    @Transactional
    @Override
    public ProductImageResponse addImage(CreateProductImageRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (Boolean.TRUE.equals(request.getIsPrimary())) {
            imageRepository.resetPrimaryImages(product.getId());
        }

        ProductImage image = ProductImage.builder()
                .product(product)
                .imageUrl(request.getImageUrl())
                .isPrimary(request.getIsPrimary() != null && request.getIsPrimary())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .build();

        if (request.getVariantId() != null) {
            ProductVariant variant = variantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));
            image.setVariant(variant);
        }

        // Lưu vào DB và dùng Mapper để chuyển sang Response trước khi trả về
        return imageMapper.toResponse(imageRepository.save(image));
    }

    @Override
    public List<ProductImageResponse> getImagesByProductId(String productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return imageMapper.toResponseList(imageRepository.findByProductId(productId));
    }

    @Override
    public void deleteImage(String imageId) {
        ProductImage image = imageRepository.findById(imageId)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));
        imageRepository.delete(image);
    }

    @Transactional
    @Override
    public ProductImageResponse updateImage(String imageId, UpdateProductImageRequest request) {
        ProductImage image = imageRepository.findById(imageId)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_NOT_FOUND));

        if (Boolean.TRUE.equals(request.getIsPrimary())) {
            imageRepository.resetPrimaryImages(image.getProduct().getId());
        }

        image.setImageUrl(request.getImageUrl());
        image.setPrimary(request.getIsPrimary() != null && request.getIsPrimary());
        image.setDisplayOrder(request.getDisplayOrder());

        if (request.getVariantId() != null) {
            ProductVariant variant = variantRepository.findById(request.getVariantId())
                    .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));
            image.setVariant(variant);
        }

        return imageMapper.toResponse(imageRepository.save(image));
    }
}