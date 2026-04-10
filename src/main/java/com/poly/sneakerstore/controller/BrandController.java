package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateBrandRequest;
import com.poly.sneakerstore.dto.request.UpdateBrandRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseSuccess addBrand(@RequestBody @Valid CreateBrandRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create brand successfully",
                brandService.createBrand(request)
        );
    }

    @PutMapping("/{brandId}")
    public ResponseSuccess updateBrand(@PathVariable String brandId, @RequestBody @Valid UpdateBrandRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Update brand successfully",
                brandService.updateBrand(brandId, request)
        );
    }

    @DeleteMapping("/{brandId}")
    public ResponseSuccess deleteBrand(@PathVariable String brandId) {
        brandService.deleteBrand(brandId);
        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Delete brand successfully"
        );
    }

    @GetMapping("/{brandId}")
    public ResponseSuccess getBrand(@PathVariable String brandId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get user successfully",
                brandService.findBrandById(brandId)
        );
    }

    @GetMapping
    public ResponseSuccess getAllBrands(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "8", required = false) int pageSize,
            @RequestParam(required = false) String sortBy
    ) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all brands successfully",
                brandService.findAllBrands(pageNo, pageSize, sortBy)
        );
    }

    @PatchMapping("/{brandId}")
    public ResponseSuccess changeStatus(@PathVariable String brandId, @RequestParam Boolean active) {
        brandService.changeStatus(brandId, active);
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Change status successfully"
        );
    }

}
