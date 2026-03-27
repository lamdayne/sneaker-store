package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
