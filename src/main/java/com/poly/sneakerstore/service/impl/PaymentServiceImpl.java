package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.config.payment.PayOSConfig;
import com.poly.sneakerstore.dto.request.CreatePaymentRequest;
import com.poly.sneakerstore.dto.response.PaymentResponse;
import com.poly.sneakerstore.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.payos.PayOS;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkRequest;
import vn.payos.model.v2.paymentRequests.CreatePaymentLinkResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PayOSConfig payOSConfig;

    @Override
    public PaymentResponse.PayOSResponse createPayOSPayment(CreatePaymentRequest request) {
        PayOS payOS = payOSConfig.payOS();
        CreatePaymentLinkRequest paymentRequest = CreatePaymentLinkRequest.builder()
                .orderCode(System.currentTimeMillis() / 1000)
                .amount(request.getAmount())
                .description(request.getDescription())
                .cancelUrl(payOSConfig.getCancelUrl())
                .returnUrl(payOSConfig.getReturnUrl())
                .expiredAt((System.currentTimeMillis() / 1000) + 120)
                .build();

        CreatePaymentLinkResponse paymentLink = payOS.paymentRequests().create(paymentRequest);

        // Format: https://img.vietqr.io/image/<BANK_ID>-<ACCOUNT_NO>-vietqr_pro.jpg?addInfo=<DESCRIPTION>&amount=<AMOUNT>
        String qrImageUrl = String.format(
                "https://img.vietqr.io/image/%s-%s-vietqr_pro.jpg?addInfo=%s&amount=%d",
                payOSConfig.getBankId(),
                paymentLink.getAccountNumber(),
                paymentLink.getDescription(),
                paymentLink.getAmount()
        );

        log.info("qrImageUrl : {}", qrImageUrl);
        log.info("payLink : {}", paymentLink.getCheckoutUrl());

        return PaymentResponse.PayOSResponse.builder()
                .paymentUrl(paymentLink.getCheckoutUrl())
                .qrImageUrl(qrImageUrl)
                .build();
    }
}
