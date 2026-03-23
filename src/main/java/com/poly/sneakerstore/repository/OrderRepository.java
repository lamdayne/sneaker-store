package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,String> {
}
