package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    @NotBlank(message = "FULL_NAME_NOT_BLANK")
    private String fullName;
    @NotBlank(message = "EMAIL_NOT_BLANK")
    private String email;
    @Size(min = 10, message = "PHONE_INVALID")
    private String phone;
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
}
