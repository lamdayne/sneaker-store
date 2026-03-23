package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateProductImageRequest;
import com.poly.sneakerstore.dto.request.UpdateProductImageRequest;
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
        var data = productImageService.addImage(request);
        return new ResponseSuccess(HttpStatus.CREATED, "Image added successfully", data);
    }

    @PutMapping("/{imageId}")
    public ResponseSuccess updateImage(@PathVariable String imageId, @RequestBody @Valid UpdateProductImageRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Updated image successfully",
                productImageService.updateImage(imageId, request)
        );
    }

    @DeleteMapping("/{imageId}")
    public ResponseSuccess deleteImage(@PathVariable String imageId) {
        productImageService.deleteImage(imageId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "Deleted image successfully");
    }

    @GetMapping("/product/{productId}")
    public ResponseSuccess getImagesByProduct(@PathVariable String productId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get images by product successfully",
                productImageService.getImagesByProductId(productId)
        );
    }
}