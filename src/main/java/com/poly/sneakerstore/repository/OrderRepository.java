package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findAllByUserId(String userId);

    @Query("select o from Order o where o.orderCode = :orderCode")
    Optional<Order> findByOrderCode(@Param("orderCode")String orderCode);
}
