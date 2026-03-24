package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateCartRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseSuccess create(@RequestBody @Valid CreateCartRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Created cart successfully",
                cartService.createCart(request)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseSuccess getByUser(@PathVariable String userId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get cart by user id successfully",
                cartService.getCartByUserId(userId)
        );
    }

    @DeleteMapping("/{cartId}")
    public ResponseSuccess delete(@PathVariable String cartId) {
        cartService.deleteCart(cartId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "Deleted cart successfully");
    }
}