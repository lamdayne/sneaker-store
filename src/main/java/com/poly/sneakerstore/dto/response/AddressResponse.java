package com.poly.sneakerstore.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponse {
    private String id;
    private String recipientName;
    private String phone;
    private String province;
    private String district;
    private String ward;
    private String streetAddress;
    private boolean defaultAddress;
    private String userId;
    @JsonProperty("isActive")
    private boolean active;
}
