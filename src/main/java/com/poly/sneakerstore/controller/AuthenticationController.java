package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.AuthenticationRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ResponseSuccess token(@RequestBody AuthenticationRequest request) throws Exception {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Create token jwt successfully",
                authenticationService.authenticate(request)
        );
    }
}
