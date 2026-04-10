package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateAddressRequest;
import com.poly.sneakerstore.dto.request.UpdateAddressRequest;
import com.poly.sneakerstore.dto.response.AddressResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.AddressMapper;
import com.poly.sneakerstore.model.Address;
import com.poly.sneakerstore.model.User;
import com.poly.sneakerstore.repository.AddressRepository;
import com.poly.sneakerstore.repository.UserRepository;
import com.poly.sneakerstore.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    @Override
    @Transactional
    public AddressResponse createAddress(CreateAddressRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (request.isDefaultAddress()) {
            addressRepository.clearDefaultAddress(user.getId());
        }
        Address address = addressMapper.toAddress(request);
        address.setUser(user);
        address.setActive(true);
        return addressMapper.toAddressResponse(addressRepository.save(address));
    }

    @Override
    @Transactional
    @PreAuthorize("@securityService.isAddressOwner(#addressId)")
    public AddressResponse updateAddress(String addressId, UpdateAddressRequest request) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        if (request.isDefaultAddress()) {
            addressRepository.clearDefaultAddress(address.getId());
        }

        addressMapper.updateAddress(address, request);

        return addressMapper.toAddressResponse(addressRepository.save(address));
    }

    @Override
    @PreAuthorize("@securityService.isAddressOwner(#addressId)")
    public void deleteAddress(String addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        address.setActive(false);
        addressRepository.save(address);
    }

    @Override
    @PreAuthorize("@securityService.isAddressOwner(#addressId)")
    public AddressResponse getAddressById(String addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
        return addressMapper.toAddressResponse(address);
    }

    @Override
    public List<AddressResponse> getAllAddress() {
        return addressMapper.toAddressResponseList(addressRepository.findAll());
    }

    @Transactional
    @Override
    public void changeAddressDefault(String addressId, String userId) {
        Address address = addressRepository.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        addressRepository.clearDefaultAddress(userId);
        address.setDefaultAddress(true);
    }

    @Override
    public List<AddressResponse> getAddressByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return addressMapper.toAddressResponseList(user.getAddresses());
    }
}
