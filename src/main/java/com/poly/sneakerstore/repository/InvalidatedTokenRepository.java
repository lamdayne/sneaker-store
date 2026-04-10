package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
}
