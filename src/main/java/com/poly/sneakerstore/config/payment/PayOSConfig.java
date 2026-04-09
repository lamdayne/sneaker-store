package com.poly.sneakerstore.config.payment;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.payos.PayOS;

@Configuration
public class PayOSConfig {
    @Value("${payment.payOs.client_id}")
    private String clientId;

    @Value("${payment.payOs.api_key}")
    private String apiKey;

    @Value("${payment.payOs.checksum_key}")
    private String checksumKey;

    @Getter
    @Value("${payment.payOs.cancel_url}")
    private String cancelUrl;

    @Getter
    @Value("${payment.payOs.return_url}")
    private String returnUrl;

    @Getter
    @Value("${payment.payOs.bankId}")
    private String bankId;

    @Bean
    public PayOS payOS() {
        return new PayOS(clientId, apiKey, checksumKey);
    }
}
