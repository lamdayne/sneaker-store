package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {
    @Query("select p.name, sum(oi.totalPrice) from OrderItem oi join oi.product p group by p.name order by sum(oi.quantity) desc")
    List<Object[]> findBestSellingProducts(Pageable pageable);
}
