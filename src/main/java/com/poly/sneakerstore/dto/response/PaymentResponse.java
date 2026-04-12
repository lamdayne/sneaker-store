package com.poly.sneakerstore.dto.response;

import lombok.Builder;
import lombok.Getter;

public class PaymentResponse {

    @Builder
    @Getter
    public static class PayOSResponse {
        private String paymentUrl;
        private String qrImageUrl;
    }
}
