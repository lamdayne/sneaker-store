package com.poly.sneakerstore.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {
    private String token;
    private boolean authenticated;
}
