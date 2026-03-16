package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateAddressRequest;
import com.poly.sneakerstore.dto.request.UpdateAddressRequest;
import com.poly.sneakerstore.dto.response.AddressResponse;
import com.poly.sneakerstore.model.Address;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toAddress(CreateAddressRequest request);

    @Mapping(source = "user.id", target = "userId")
    AddressResponse toAddressResponse(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // NOT MAP NULL PROPERTY
    void updateAddress(@MappingTarget Address address, UpdateAddressRequest request);

    List<AddressResponse> toAddressResponseList(List<Address> addressList);
}
