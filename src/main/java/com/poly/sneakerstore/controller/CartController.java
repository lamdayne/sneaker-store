package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateCartRequest;
import com.poly.sneakerstore.dto.response.CartResponse;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseSuccess createCart(@RequestBody CreateCartRequest request) {
        CartResponse data = cartService.createCart(request);
        return new ResponseSuccess(HttpStatus.CREATED, "Cart created successfully", data);
    }

    @GetMapping("/user/{userId}")
    public ResponseSuccess getCart(@PathVariable String userId) {
        CartResponse data = cartService.getCartByUserId(userId);
        return new ResponseSuccess(HttpStatus.OK, "Get cart successfully", data);
    }
}