package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreatePaymentRequest;
import com.poly.sneakerstore.dto.response.PaymentResponse;

public interface PaymentService {
    PaymentResponse.PayOSResponse createPayOSPayment(CreatePaymentRequest createPaymentRequest);
}
