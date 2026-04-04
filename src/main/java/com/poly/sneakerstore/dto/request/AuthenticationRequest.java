package com.poly.sneakerstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
