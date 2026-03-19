package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateAddressRequest;
import com.poly.sneakerstore.dto.request.UpdateAddressRequest;
import com.poly.sneakerstore.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(CreateAddressRequest request);
    AddressResponse updateAddress(String addressId, UpdateAddressRequest request);
    void deleteAddress(String addressId);
    AddressResponse getAddressById(String addressId);
    List<AddressResponse> getAllAddress();
    void changeAddressDefault(String addressId, String userId);
    List<AddressResponse> getAddressByUserId(String userId);
}
