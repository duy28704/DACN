package com.example.depot_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.depot_service.entity.Inventory;
import com.example.depot_service.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class DepotController {
    private final InventoryService inventoryService;

    @GetMapping("/hello")
    public String hello() {
        return "Server is running on port 8083!";
    }
    
    // Nhập kho
    @PostMapping("/add")
    public ResponseEntity<String> addStock(
            @RequestParam String productId,
            @RequestParam String color,
            @RequestParam String size,
            @RequestParam int quantity) {

        boolean result = inventoryService.addStock(productId, color, size, quantity);
        return result
                ? ResponseEntity.ok("Thêm tồn kho thành công")
                : ResponseEntity.badRequest().body("Không thể thêm tồn kho");
    }

    // Xuất kho
    @PostMapping("/deduct")
    public ResponseEntity<String> deductStock(
            @RequestParam String productId,
            @RequestParam String color,
            @RequestParam String size,
            @RequestParam int quantity) {

        boolean result = inventoryService.deductStock(productId, color, size, quantity);
        return result
                ? ResponseEntity.ok("Xuất kho thành công")
                : ResponseEntity.badRequest().body("Không đủ tồn kho hoặc không tìm thấy sản phẩm");
    }

    // Kiểm tra tồn kho
    @GetMapping("/check")
    public ResponseEntity<Integer> getStock(
            @RequestParam String productId,
            @RequestParam String color,
            @RequestParam String size) {

        int quantity = inventoryService.getStock(productId, color, size);
        return ResponseEntity.ok(quantity);
    }

    // Lấy toàn bộ tồn kho
    @GetMapping("/all")
    public ResponseEntity<List<Inventory>> getAllStock() {
        return ResponseEntity.ok(inventoryService.getAllStock());
    }

    // Xoá tồn kho theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        boolean result = inventoryService.deleteStock(id);
        return result
                ? ResponseEntity.ok("Xoá tồn kho thành công")
                : ResponseEntity.badRequest().body("Không tìm thấy tồn kho với id=" + id);
    }
}
