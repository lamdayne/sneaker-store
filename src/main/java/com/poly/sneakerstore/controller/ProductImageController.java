package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateProductImageRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.impl.ProductImageServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-images")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageServiceImpl productImageService;

    @PostMapping
    public ResponseSuccess addImage(@RequestBody @Valid CreateProductImageRequest request) {
        productImageService.addImage(request);
        return new ResponseSuccess(HttpStatus.CREATED, "Image added successfully");
    }
}