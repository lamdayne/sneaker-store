package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreatePaymentRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payos")
    public ResponseSuccess payOS(@RequestBody CreatePaymentRequest request) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Success",
                paymentService.createPayOSPayment(request)
        );
    }

}
