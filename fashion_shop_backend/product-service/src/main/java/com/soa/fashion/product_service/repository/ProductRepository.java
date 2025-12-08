package com.soa.fashion.product_service.repository;

import com.soa.fashion.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsByProductId(String productId);
    Optional<Product> findByProductId(String productId);
    void deleteAllByProduct(Product product);
    @Query("SELECT p FROM Product p WHERE p.deleted = false")
    List<Product> getAllActiveProducts();
}
