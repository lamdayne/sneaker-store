package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.CartItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    List<CartItem> findByUserId(String userId);

    CartItem findByUserIdAndProductId(String userId, String productId);

    @Modifying
    @Query("delete from CartItem c where c.user.id = :userId")
    void deleteAllByUserId(@Param("userId") String userId);

    @Query("""
                select p.name, sum(oi.totalPrice)
                from OrderItem oi
                join Product p
                group by p.name
                order by sum(oi.totalPrice) desc
            """)
    List<Object[]> findBestSellingProduct(Pageable pageable);
}