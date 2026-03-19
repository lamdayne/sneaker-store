package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateBrandRequest;
import com.poly.sneakerstore.dto.request.UpdateBrandRequest;
import com.poly.sneakerstore.dto.response.BrandResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.BrandMapper;
import com.poly.sneakerstore.model.Brand;
import com.poly.sneakerstore.repository.BrandRepository;
import com.poly.sneakerstore.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandResponse createBrand(CreateBrandRequest request) {
        Brand brand = brandMapper.toBrand(request);
        brand.setActive(true);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    public BrandResponse updateBrand(String brandId, UpdateBrandRequest request) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));

        brandMapper.updateBrand(brand, request);
        return brandMapper.toBrandResponse(brandRepository.save(brand));
    }

    @Override
    public void deleteBrand(String brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        brand.setActive(false);
        brandRepository.save(brand);
    }

    @Override
    public BrandResponse findBrandById(String brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        return brandMapper.toBrandResponse(brand);
    }

    @Override
    public List<BrandResponse> findAllBrands() {
        return brandMapper.toBrandResponseList(brandRepository.findAll());
    }

    @Override
    public void changeStatus(String brandId, Boolean active) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_FOUND));
        if (active == null) {
            throw new AppException(ErrorCode.ACTIVE_NOT_NULL);
        }

        brand.setActive(active);
        brandRepository.save(brand);
    }
}
