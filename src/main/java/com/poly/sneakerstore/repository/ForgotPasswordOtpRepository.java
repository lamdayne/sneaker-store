package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.ForgotPasswordOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordOtpRepository extends JpaRepository<ForgotPasswordOtp, Integer> {
    Optional<ForgotPasswordOtp> findByCodeAndEmail(String code, String email);
}
