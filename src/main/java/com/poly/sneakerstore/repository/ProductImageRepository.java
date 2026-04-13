package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.ProductImage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, String> {
    List<ProductImage> findByProductId(String productId);

    @Modifying
    @Query("UPDATE ProductImage p SET p.isPrimary = false WHERE p.product.id = :productId")
    void resetPrimaryImages(String productId);
}
