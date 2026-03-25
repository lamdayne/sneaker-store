package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.AddCartItemRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart-items")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseSuccess add(@RequestBody @Valid AddCartItemRequest request) {

        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Add cart item successfully",
                cartItemService.add(request)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseSuccess get(@PathVariable String userId) {

        return new ResponseSuccess(
                HttpStatus.OK,
                "Get cart items successfully",
                cartItemService.getByUser(userId)
        );
    }

    @PutMapping("/{itemId}")
    public ResponseSuccess update(
            @PathVariable String itemId,
            @RequestParam int quantity) {

        return new ResponseSuccess(
                HttpStatus.OK,
                "Update cart item successfully",
                cartItemService.update(itemId, quantity)
        );
    }

    @DeleteMapping("/{itemId}")
    public ResponseSuccess delete(@PathVariable String itemId) {

        cartItemService.delete(itemId);

        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Delete cart item successfully"
        );
    }

    @DeleteMapping("/user/{userId}")
    public ResponseSuccess clear(@PathVariable String userId) {

        cartItemService.clear(userId);

        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Clear cart successfully"
        );
    }
}