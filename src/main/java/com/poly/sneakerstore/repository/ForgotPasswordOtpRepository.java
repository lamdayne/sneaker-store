package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.ForgotPasswordOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordOtpRepository extends JpaRepository<ForgotPasswordOtp, Integer> {
    boolean existsByCodeAndEmail(String code, String email);
}
