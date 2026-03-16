package com.poly.sneakerstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class UpdateAddressRequest implements Serializable {
    @NotBlank(message = "RECIPIENT_NAME_NOT_BLANK")
    private String recipientName;

    @Size(min = 10, message = "PHONE_INVALID")
    private String phone;

    @NotBlank(message = "PROVINCE_NOT_BLANK")
    private String province;

    @NotBlank(message = "DISTRICT_NOT_BLANK")
    private String district;

    @NotBlank(message = "WARD_NOT_BLANK")
    private String ward;

    @NotBlank(message = "STREET_ADDRESS_NOT_BLANK")
    private String streetAddress;

    private boolean defaultAddress;
}
