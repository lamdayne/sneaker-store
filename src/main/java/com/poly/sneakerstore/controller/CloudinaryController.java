package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cloudinary")
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/sign-info")
    public ResponseSuccess getSignedInfo() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get signed info successfully",
                cloudinaryService.getPresignedInfo()
        );
    }

}
