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
    RECIPIENT_NAME_NOT_BLANK(1008, "recipientName can not blank", HttpStatus.BAD_REQUEST),
    PROVINCE_NOT_BLANK(1009, "province can not blank", HttpStatus.BAD_REQUEST),
    DISTRICT_NOT_BLANK(10010, "district can not blank", HttpStatus.BAD_REQUEST),
    WARD_NOT_BLANK(10011, "ward can not blank", HttpStatus.BAD_REQUEST),
    STREET_ADDRESS_NOT_BLANK(10012, "streetAddress can not blank", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND(10013, "Address not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(10014, "Category not found", HttpStatus.NOT_FOUND),
    CATEGORY_NAME_NOT_BLANK(10015, "Category name can not blank", HttpStatus.BAD_REQUEST),
    CATEGORY_IMG_NOT_BLANK(10016, "Category image can not blank", HttpStatus.BAD_REQUEST),
    DISPLAY_ORDER(10017, "Display order must be greater than or equal to 1", HttpStatus.BAD_REQUEST),
    ACTIVE_NOT_NULL(10028, "Active not null", HttpStatus.BAD_REQUEST),
    BRAND_NOT_FOUND(10029, "Brand not found", HttpStatus.NOT_FOUND),
    BRAND_NAME_NOT_BLANK(10030, "Brand name can not blank", HttpStatus.BAD_REQUEST),
    BRAND_LOGO_NOT_BLANK(10031, "Brand logo can not blank", HttpStatus.BAD_REQUEST),
    BRAND_DESC_NOT_BLANK(10032, "Brand description can not blank", HttpStatus.BAD_REQUEST),
    ;
    private int status;
    private String message;
    private HttpStatus httpStatus;

}
