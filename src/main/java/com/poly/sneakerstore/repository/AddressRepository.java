package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
    Optional<Address> findByIdAndUserId(String addressId, String userId);

    @Modifying
    @Query("UPDATE Address SET defaultAddress = false WHERE user.id = :userId")
    void clearDefaultAddress(String userId);
}
