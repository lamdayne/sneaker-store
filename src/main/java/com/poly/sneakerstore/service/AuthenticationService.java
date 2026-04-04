package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.AuthenticationRequest;
import com.poly.sneakerstore.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
