package com.poly.sneakerstore.service;

import com.nimbusds.jose.JOSEException;
import com.poly.sneakerstore.dto.request.AuthenticationRequest;
import com.poly.sneakerstore.dto.request.IntrospectRequest;
import com.poly.sneakerstore.dto.request.LogoutRequest;
import com.poly.sneakerstore.dto.request.RefreshTokenRequest;
import com.poly.sneakerstore.dto.response.AuthenticationResponse;
import com.poly.sneakerstore.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
