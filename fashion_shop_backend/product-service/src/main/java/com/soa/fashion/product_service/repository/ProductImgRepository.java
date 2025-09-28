package com.soa.fashion.product_service.repository;

import com.soa.fashion.product_service.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImgRepository extends JpaRepository<ProductImg,Long> {
}
