package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateProductRequest;
import com.poly.sneakerstore.dto.request.UpdateProductRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseSuccess createProduct(@RequestBody @Valid CreateProductRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create product successfully",
                productService.createProduct(request)
        );
    }

    @PutMapping("/{productId}")
    public ResponseSuccess updateProduct(@PathVariable("productId") String productId, @RequestBody @Valid UpdateProductRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Update product successfully",
                productService.updateProduct(productId, request)
        );
    }

    @DeleteMapping("/{productId}")
    public ResponseSuccess deleteProduct(@PathVariable("productId") String productId) {
        productService.deleteProduct(productId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "Delete product successfully");
    }

    @GetMapping("/{productId}")
    public ResponseSuccess getProduct(@PathVariable("productId") String productId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get user by id successfully",
                productService.findById(productId)
        );
    }

    @GetMapping
    public ResponseSuccess getAllProducts() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all product successfully",
                productService.findAll()
        );
    }

}
