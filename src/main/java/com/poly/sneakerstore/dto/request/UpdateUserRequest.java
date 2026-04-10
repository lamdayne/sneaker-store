package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
    @NotBlank(message = "FULL_NAME_NOT_BLANK")
    private String fullName;

    @NotBlank(message = "EMAIL_NOT_BLANK")
    private String email;

    @Size(min = 10, message = "PHONE_INVALID")
    private String phone;

    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotBlank(message = "PASSWORD_CAN_NOT_BLANK")
    private String password;
    private String avatarUrl;
    private boolean active;
}
