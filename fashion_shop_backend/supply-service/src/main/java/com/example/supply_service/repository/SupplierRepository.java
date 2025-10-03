package com.example.supply_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.supply_service.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
