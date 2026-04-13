package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateProductVariantRequest;
import com.poly.sneakerstore.dto.request.UpdateProductVariantRequest;
import com.poly.sneakerstore.dto.response.ProductVariantResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.ProductVariantMapper;
import com.poly.sneakerstore.model.Product;
import com.poly.sneakerstore.model.ProductVariant;
import com.poly.sneakerstore.repository.ProductRepository;
import com.poly.sneakerstore.repository.ProductVariantRepository;
import com.poly.sneakerstore.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository variantRepository;
    private final ProductRepository productRepository;
    private final ProductVariantMapper variantMapper;

    @Override
    public ProductVariantResponse createVariant(CreateProductVariantRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        boolean isExisted = variantRepository.existsByProductIdAndSizeAndColorAndColorHexAndStockQuantityAndPriceOverride(
                request.getProductId(),
                request.getSize(),
                request.getColor(),
                request.getColorHex(),
                request.getStockQuantity(),
                request.getPriceOverride()
        );

        if (isExisted) {
            throw new AppException(ErrorCode.VARIANT_EXISTS);
        }

        ProductVariant variant = variantMapper.toEntity(request);
        variant.setProduct(product);
        variant.setActive(true);

        return variantMapper.toResponse(variantRepository.save(variant));
    }

    @Override
    public List<ProductVariantResponse> getByProductId(String productId) {
        return variantMapper.toListResponse(variantRepository.findByProductId(productId));
    }

    @Override
    public void deleteVariant(String variantId) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));
        variant.setActive(false);
        variantRepository.save(variant);
    }

    @Override
    public ProductVariantResponse updateVariant(String variantId, UpdateProductVariantRequest request) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));

        variant.setSize(request.getSize());
        variant.setColor(request.getColor());
        variant.setColorHex(request.getColorHex());
        variant.setStockQuantity(request.getStockQuantity());
        variant.setPriceOverride(request.getPriceOverride());
        variant.setActive(request.getActive());

        return variantMapper.toResponse(variantRepository.save(variant));
    }

    @Override
    public ProductVariantResponse getVariantById(String variantId) {
        ProductVariant variant = variantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.VARIANT_NOT_FOUND));
        return variantMapper.toResponse(variant);
    }

    @Override
    public List<ProductVariantResponse> getAllVariants() {
        return variantMapper.toListResponse(variantRepository.findAll());
    }
}