package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateProductVariantRequest;
import com.poly.sneakerstore.dto.request.UpdateProductVariantRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService variantService;

    @PostMapping
    public ResponseSuccess create(@RequestBody @Valid CreateProductVariantRequest request) {
        return new ResponseSuccess(HttpStatus.CREATED, "Created variant successfully", variantService.createVariant(request));
    }

    @PutMapping("/{variantId}")
    public ResponseSuccess update(@PathVariable String variantId, @RequestBody @Valid UpdateProductVariantRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Updated variant successfully",
                variantService.updateVariant(variantId, request)
        );
    }

    @GetMapping("/{variantId}")
    public ResponseSuccess getById(@PathVariable String variantId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get variant by id successfully",
                variantService.getVariantById(variantId)
        );
    }

    @GetMapping
    public ResponseSuccess getAll() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all variants successfully",
                variantService.getAllVariants()
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseSuccess getByProduct(@PathVariable String productId) {
        return new ResponseSuccess(HttpStatus.OK, "Get variants successfully", variantService.getByProductId(productId));
    }

    @DeleteMapping("/{variantId}")
    public ResponseSuccess delete(@PathVariable String variantId) {
        variantService.deleteVariant(variantId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "Deleted variant successfully");
    }
}