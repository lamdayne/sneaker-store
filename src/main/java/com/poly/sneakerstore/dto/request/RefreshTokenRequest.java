package com.poly.sneakerstore.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshTokenRequest {
    private String token;
}
