package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateBrandRequest;
import com.poly.sneakerstore.dto.request.UpdateBrandRequest;
import com.poly.sneakerstore.dto.response.BrandResponse;
import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.BrandMapper;
import com.poly.sneakerstore.model.Brand;
import com.poly.sneakerstore.repository.BrandRepository;
import com.poly.sneakerstore.service.BrandService;
import com.poly.sneakerstore.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final PageableUtil pageableUtil;

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
    public PageResponse<?> findAllBrands(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = pageableUtil.createPageable(pageNo, pageSize, sortBy);
        Page<Brand> brands = brandRepository.findAll(pageable);
        List<BrandResponse> response = brands.stream().map(brandMapper::toBrandResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(brands.getTotalPages())
                .totalElements((int) brands.getTotalElements())
                .items(response)
                .build();
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
