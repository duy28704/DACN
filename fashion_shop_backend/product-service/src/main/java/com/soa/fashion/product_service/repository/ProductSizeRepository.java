package com.soa.fashion.product_service.repository;

import com.soa.fashion.product_service.entity.Product;
import com.soa.fashion.product_service.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
    void deleteAllByProduct(Product product);
}
