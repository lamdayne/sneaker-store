package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    List<CartItem> findByUserId(String userId);

    CartItem findByUserIdAndProductId(String userId, String productId);
}