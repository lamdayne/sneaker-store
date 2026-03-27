package com.poly.sneakerstore.repository;

import com.poly.sneakerstore.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {
    List<ProductVariant> findByProductId(String productId);
    boolean existsByProductIdAndSizeAndColorAndColorHexAndStockQuantityAndPriceOverride(
            String productId, String size, String color, String colorHex, Integer stockQuantity, Double priceOverride
    );
}
