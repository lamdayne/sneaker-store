package com.poly.sneakerstore.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
    INVALID_KEY(2000, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1001, "User not found", HttpStatus.NOT_FOUND),
    FULL_NAME_NOT_BLANK(1002, "fullName can not blank", HttpStatus.BAD_REQUEST),
    EMAIL_NOT_BLANK(1003, "email can not blank", HttpStatus.BAD_REQUEST),
    PHONE_INVALID(1004, "phone has must be greater than 10 character", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1005, "password has must be greater than 8 character", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTS(1006, "email already exists", HttpStatus.BAD_REQUEST),
    PHONE_EXISTS(1007, "phone already exists", HttpStatus.BAD_REQUEST),
    ;
    private int status;
    private String message;
    private HttpStatus httpStatus;

}
