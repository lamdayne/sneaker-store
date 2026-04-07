package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseSuccess addCartItem(@RequestBody AddCartItemRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Add to cart successfully",
                cartItemService.addCartItem(request)
        );
    }

    @DeleteMapping("{cartItemId}")
    public ResponseSuccess deleteCartItem(@PathVariable("cartItemId") String cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Delete item successfully"
        );
    }

    @GetMapping("/my-cart")
    public ResponseSuccess getMyCart() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get cart items successfully",
                cartItemService.getMyCartItem()
        );
    }

}