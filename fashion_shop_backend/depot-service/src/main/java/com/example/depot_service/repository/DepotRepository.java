package com.example.depot_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.depot_service.entity.Inventory;

@Repository
public interface DepotRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductIdAndColorAndSize(String productId, String color, String size);
}
