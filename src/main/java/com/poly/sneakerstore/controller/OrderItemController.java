package com.poly.sneakerstore.controller;


import com.poly.sneakerstore.dto.request.CreateOrderItemRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderItemRequest;
import com.poly.sneakerstore.dto.response.OrderItemResponse;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseSuccess create(@RequestBody CreateOrderItemRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create order item success",
                orderItemService.create(request)
        );
    }
    @GetMapping
    public ResponseSuccess getAll() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all order item success",
                orderItemService.getAll()
        );
    }
    @GetMapping("/order/{orderId}")
    public ResponseSuccess getByOrder(@PathVariable String orderId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get by oder item success",
                orderItemService.getByOrder(orderId)
        );
    }

    @PutMapping("/{id}")
    public ResponseSuccess update(@PathVariable String id, @RequestBody UpdateOrderItemRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Update order item success",
                orderItemService.update(id, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseSuccess getById(@PathVariable String id) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get order item by id success",
                orderItemService.getById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess delete(@PathVariable String id) {
        orderItemService.delete(id);
        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Delete order item success"
        );
    }
}
