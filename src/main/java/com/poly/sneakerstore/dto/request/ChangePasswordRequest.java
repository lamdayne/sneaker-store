package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotBlank(message = "PASSWORD_CAN_NOT_BLANK")
    private String password;

    @NotBlank(message = "EMAIL_NOT_BLANK")
    private String email;

    @NotBlank(message = "CODE_CAN_NOT_BLANK")
    private String code;
}
