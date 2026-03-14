package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
