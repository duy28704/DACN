package com.example.depot_service.service;

import java.util.List;

import com.example.depot_service.entity.Inventory;

public interface DepotInterface {
    boolean addStock(String productId, String color, String size, int quantity);

    boolean deductStock(String productId, String color, String size, int quantity);

    int getStock(String productId, String color, String size);

    List<Inventory> getAllStock();

    boolean deleteStock(Long id);
}
