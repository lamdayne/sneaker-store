package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    List<ProductVariant> findByProductId(String productId);

    Optional<ProductVariant> findByProductIdAndColor(String productId, String color);
}
