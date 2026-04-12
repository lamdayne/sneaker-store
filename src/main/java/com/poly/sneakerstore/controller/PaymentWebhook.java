package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.config.payment.PayOSConfig;
import com.poly.sneakerstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.payos.PayOS;
import vn.payos.model.webhooks.Webhook;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webhook")
public class PaymentWebhook {

    private final PayOSConfig payOSConfig;
    private final OrderService orderService;

    @PostMapping("/payos")
    public ResponseEntity<String> handleWebhook(@RequestBody Webhook webhook) {
        PayOS payOS = payOSConfig.payOS();
        try {
            var data = payOS.webhooks().verify(webhook);
            orderService.updatePaymentStatus(data, data.getDescription());
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            System.err.println("Webhook không hợp lệ: " + e.getMessage());
            return ResponseEntity.badRequest().body("Invalid webhook");
        }
    }

}
