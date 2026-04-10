package com.poly.sneakerstore.controller;

import com.nimbusds.jose.JOSEException;
import com.poly.sneakerstore.dto.request.AuthenticationRequest;
import com.poly.sneakerstore.dto.request.IntrospectRequest;
import com.poly.sneakerstore.dto.request.LogoutRequest;
import com.poly.sneakerstore.dto.request.RefreshTokenRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.AuthenticationService;
import com.poly.sneakerstore.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/token")
    public ResponseSuccess token(
            @RequestBody AuthenticationRequest request
    ) throws Exception {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create token jwt successfully",
                authenticationService.authenticate(request)
        );
    }

    @PostMapping("/introspect")
    public ResponseSuccess introspect(@RequestBody IntrospectRequest request) throws Exception {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Introspect successfully",
                authenticationService.introspect(request)
        );
    }

    @PostMapping("/logout")
    public ResponseSuccess logout(@RequestBody LogoutRequest request) throws Exception {
        authenticationService.logout(request);
        return new ResponseSuccess(
                HttpStatus.OK,
                "Logout successfully"
        );
    }

    @GetMapping("/me")
    public ResponseSuccess myInfo() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "My info successfully",
                userService.myInfo()
        );
    }

    @PostMapping("/refresh")
    public ResponseSuccess refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Refresh Token",
                authenticationService.refreshToken(request)
        );
    }
}
