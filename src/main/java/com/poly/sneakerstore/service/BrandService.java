package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateBrandRequest;
import com.poly.sneakerstore.dto.request.UpdateBrandRequest;
import com.poly.sneakerstore.dto.response.BrandResponse;
import com.poly.sneakerstore.dto.response.PageResponse;

import java.util.List;

public interface BrandService {
    BrandResponse createBrand(CreateBrandRequest request);
    BrandResponse updateBrand(String brandId, UpdateBrandRequest request);
    void deleteBrand(String brandId);
    BrandResponse findBrandById(String brandId);
    PageResponse<?> findAllBrands(int pageNo, int pageSize, String sortBy);
    void changeStatus(String brandId, Boolean active);
}
