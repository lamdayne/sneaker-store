package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CartRequest;
import com.poly.sneakerstore.dto.response.CartResponse;
import com.poly.sneakerstore.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // Lấy giỏ hàng của người dùng
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getMyCart(userId));
    }

    // Thêm sản phẩm vào giỏ
    @PostMapping("/{userId}")
    public ResponseEntity<String> add(@PathVariable String userId, @RequestBody CartRequest request) {
        cartService.addToCart(userId, request);
        return ResponseEntity.ok("Sản phẩm đã được thêm vào giỏ hàng");
    }

    // Cập nhật số lượng (Dùng Patch hoặc Put)
    @PatchMapping("/item/{cartItemId}")
    public ResponseEntity<String> updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {
        cartService.updateQuantity(cartItemId, quantity);
        return ResponseEntity.ok("Cập nhật số lượng thành công");
    }

    // Xóa 1 sản phẩm khỏi giỏ
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<String> remove(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return ResponseEntity.ok("Đã xóa sản phẩm khỏi giỏ hàng");
    }

    // Xóa sạch giỏ hàng
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clear(@PathVariable String userId) {
        cartService.clearCart(userId);
        return ResponseEntity.ok("Đã làm trống giỏ hàng");
    }
}