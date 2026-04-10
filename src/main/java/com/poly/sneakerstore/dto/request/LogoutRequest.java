package com.poly.sneakerstore.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogoutRequest {
    private String token;
}
