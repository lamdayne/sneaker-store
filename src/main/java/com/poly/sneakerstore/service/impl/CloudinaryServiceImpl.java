package com.poly.sneakerstore.service.impl;

import com.cloudinary.Cloudinary;
import com.poly.sneakerstore.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    @Value("${cloudinary.cloud_name}")
    private String cloudName;

    @Value("${cloudinary.api_key}")
    private String apiKey;

    @Value("${cloudinary.api_secret}")
    private String apiSecret;

    private final Cloudinary cloudinary;

    @Override
    public Map<String, Object> getPresignedInfo() {
        long timestamp = System.currentTimeMillis() / 1000L;
        Map<String, Object> params = new HashMap<>();
        params.put("timestamp", timestamp);
        params.put("folder", "sneaker_store");

        String signature = cloudinary.apiSignRequest(params, apiSecret);

        params.put("signature", signature);
        params.put("api_key", apiKey);
        params.put("cloud_name", cloudName);

        return params;
    }
}
