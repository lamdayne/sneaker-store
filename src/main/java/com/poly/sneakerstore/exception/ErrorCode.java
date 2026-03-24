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
    ACTIVE_NOT_NULL(10018, "Active not null", HttpStatus.BAD_REQUEST),
    BRAND_NOT_FOUND(10019, "Brand not found", HttpStatus.NOT_FOUND),
    BRAND_NAME_NOT_BLANK(10020, "Brand name can not blank", HttpStatus.BAD_REQUEST),
    BRAND_LOGO_NOT_BLANK(10021, "Brand logo can not blank", HttpStatus.BAD_REQUEST),
    BRAND_DESC_NOT_BLANK(10022, "Brand description can not blank", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(10023, "Product not found", HttpStatus.NOT_FOUND),
    PRODUCT_NAME_NOT_BLANK(10024, "Product name can not blank", HttpStatus.BAD_REQUEST),
    PRODUCT_DESC_NOT_BLANK(10025, "Product description can not blank", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_NOT_BLANK(10026, "Product price can not blank", HttpStatus.BAD_REQUEST),
    PRODUCT_MATERIAL_NOT_BLANK(10027, "Product material can not blank", HttpStatus.BAD_REQUEST),
    FEATURED_NOT_NULL(10028, "Featured can not null", HttpStatus.BAD_REQUEST),
    BRAND_ID_NOT_BLANK(10029, "brandId can not blank", HttpStatus.BAD_REQUEST),
    CATEGORY_ID_NOT_BLANK(10030, "categoryId can not blank", HttpStatus.BAD_REQUEST),
    PRODUCT_ID_NOT_BLANK(10031, "Product ID cannot be blank", HttpStatus.BAD_REQUEST),
    SIZE_NOT_BLANK(10032, "Size cannot be blank", HttpStatus.BAD_REQUEST),
    COLOR_NOT_BLANK(10033, "Color cannot be blank", HttpStatus.BAD_REQUEST),
    STOCK_INVALID(10034, "Stock quantity must be greater than or equal to 0", HttpStatus.BAD_REQUEST),
    VARIANT_NOT_FOUND(10035, "Product variant not found", HttpStatus.NOT_FOUND),
    IMAGE_NOT_FOUND(10036, "Product image not found", HttpStatus.NOT_FOUND),
    IMAGE_URL_NOT_BLANK(10037, "Image URL cannot be blank", HttpStatus.BAD_REQUEST),
    ORDER_NOT_FOUND(10038, "Order not found", HttpStatus.NOT_FOUND),
    ORDER_ITEM_NOT_FOUND(10039, "Order item not found", HttpStatus.NOT_FOUND),
    QUANTITY_INVALID(10040, "Quantity must be greater than 0", HttpStatus.BAD_REQUEST),
    UNIT_PRICE_NOT_NULL(10042, "Unit price cannot be null", HttpStatus.BAD_REQUEST),
    UNIT_PRICE_INVALID(10043, "Unit price must be greater than 0", HttpStatus.BAD_REQUEST),
    QUANTITY_NOT_NULL(10044, "Quantity cannot be null", HttpStatus.BAD_REQUEST),
    VARIANT_ID_NOT_BLANK(10041, "Variant id cannot be blank", HttpStatus.BAD_REQUEST),
    PRICE_NOT_NULL(10042, "Price can not null", HttpStatus.BAD_REQUEST),
    COLOR_HEX_NOT_BLANK(10043, "colorHex cannot be blank", HttpStatus.BAD_REQUEST),
    STOCK_NOT_NULL(10044, "Stock cannot be null", HttpStatus.BAD_REQUEST),
    DISPLAY_ORDER_NOT_NULL(10045, "displayOrder cannot be null", HttpStatus.BAD_REQUEST),
    IS_PRIMARY_NOT_NULL(10046, "isPrimary cannot be null", HttpStatus.BAD_REQUEST),
    TOTAL_PRICE_NOT_NULL(10047, "totalPrice cannot be null", HttpStatus.BAD_REQUEST),
    TOTAL_PRICE_INVALID(10048, "totalPrice must be greater than 0", HttpStatus.BAD_REQUEST),
    CART_NOT_FOUND(10049, "Cart not found", HttpStatus.NOT_FOUND),
    USER_ID_NOT_BLANK(10050, "User ID cannot be blank", HttpStatus.BAD_REQUEST),
    CART_ID_NOT_BLANK(10051, "Cart ID cannot be blank", HttpStatus.BAD_REQUEST);

    private int status;
    private String message;
    private HttpStatus httpStatus;

}
