package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateOrderRequest;
import com.poly.sneakerstore.dto.request.UpdateOrderRequest;
import com.poly.sneakerstore.dto.response.OrderResponse;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.OrderService;
import com.poly.sneakerstore.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseSuccess createOrder(@RequestBody @Valid CreateOrderRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create Order successfully",
                orderService.createOrder(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseSuccess update(
            @PathVariable("id") String id,
            @RequestBody @Valid UpdateOrderRequest request
    ) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Update Order successfully",
                orderService.updateOrder(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,"Delete Order successfully"
        );
    }

    @GetMapping("/{id}")
    public ResponseSuccess getById(@PathVariable String id) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get order by id successfully",
                orderService.getOrderById(id)
        );
    }

    @GetMapping
    public ResponseSuccess getAll(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "8", required = false) int pageSize,
            @RequestParam(required = false) String sortBy
    ) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all order successfully",
                orderService.getAllOrders(pageNo, pageSize, sortBy)
        );
    }

    @GetMapping("/me/all")
    public ResponseSuccess getAllMe() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all order successfully",
                orderService.getAllMyOrders()
        );
    }

    @GetMapping("{orderCode}/payment-status")
    public ResponseSuccess checkOrderStatus(@PathVariable String orderCode) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get payment status success",
                orderService.getPaymentStatusByOrderCode(orderCode)
        );
    }

    @PostMapping("/cancel/{orderCode}")
    public ResponseSuccess cancelOrder(@PathVariable String orderCode) {
        orderService.cancelOrder(orderCode);
        return new ResponseSuccess(
                HttpStatus.OK,
                "Cancel order successfully"
        );
    }

    @PostMapping("/status/{orderCode}")
    public ResponseSuccess changeStatus(@PathVariable String orderCode, @RequestParam String status) {
        orderService.changeStatus(orderCode, status);
        return new ResponseSuccess(
                HttpStatus.OK,
                "Change status successfully"
        );
    }
}
