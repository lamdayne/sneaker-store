package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateAddressRequest;
import com.poly.sneakerstore.dto.request.UpdateAddressRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseSuccess createAddress(@RequestBody @Valid CreateAddressRequest request) {
        return new ResponseSuccess(
                HttpStatus.CREATED,
                "Created address successfully",
                addressService.createAddress(request)
        );
    }

    @PutMapping("/{addressId}")
    public ResponseSuccess updateAddress(@PathVariable String addressId, @RequestBody @Valid UpdateAddressRequest request) {
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Updated address successfully",
                addressService.updateAddress(addressId, request)
        );
    }

    @DeleteMapping("/{addressId}")
    public ResponseSuccess deleteAddress(@PathVariable String addressId) {
        addressService.deleteAddress(addressId);
        return new ResponseSuccess(
                HttpStatus.NO_CONTENT,
                "Delete address successfully"
        );
    }

    @GetMapping("/{addressId}")
    public ResponseSuccess getAddressBy(@PathVariable String addressId) {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get address by id successfully",
                addressService.getAddressById(addressId)
        );
    }

    @GetMapping
    public ResponseSuccess getAllAddress() {
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get all address successfully",
                addressService.getAllAddress()
        );
    }

    @PatchMapping("/{addressId}")
    public ResponseSuccess changeDefaultAddress(@PathVariable String addressId, @RequestParam String userId) {
        addressService.changeAddressDefault(addressId, userId);
        return new ResponseSuccess(
                HttpStatus.ACCEPTED,
                "Change default address successfully"
        );
    }

}
