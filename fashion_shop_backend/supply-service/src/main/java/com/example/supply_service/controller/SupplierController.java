package com.example.supply_service.controller;

import com.example.supply_service.dto.SupplierDTO;
import com.example.supply_service.service.SupplierServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "*") // Cho phép frontend gọi API từ domain khác
public class SupplierController {

    @Autowired
    private SupplierServiceInterface supplierService;

    @GetMapping("/hello")
    public String hello() {
        return "Server is running on port 8084!";
    }
    

    // Lấy toàn bộ danh sách nhà cung cấp
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    // Lấy chi tiết nhà cung cấp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplier = supplierService.getSupplierById(id);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplier);
    }

    // Tạo mới nhà cung cấp
    @PostMapping
    public ResponseEntity<String> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        boolean created = supplierService.createSupplier(supplierDTO);
        return created ? ResponseEntity.ok("Supplier created successfully")
                       : ResponseEntity.badRequest().body("Failed to create supplier");
    }

    // Cập nhật nhà cung cấp
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        boolean updated = supplierService.updateSupplier(id, supplierDTO);
        return updated ? ResponseEntity.ok("Supplier updated successfully")
                       : ResponseEntity.notFound().build();
    }

    // Xóa nhà cung cấp
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
        boolean deleted = supplierService.deleteSupplier(id);
        return deleted ? ResponseEntity.ok("Supplier deleted successfully")
                       : ResponseEntity.notFound().build();
    }

    // Ngừng hoạt động
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateSupplier(@PathVariable Long id) {
        boolean deactivated = supplierService.deactivateSupplier(id);
        return deactivated ? ResponseEntity.ok("Supplier deactivated successfully")
                           : ResponseEntity.notFound().build();
    }

    // Cho phép hoạt động trở lại
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateSupplier(@PathVariable Long id) {
        boolean activated = supplierService.activateSupplier(id);
        return activated ? ResponseEntity.ok("Supplier activated successfully")
                         : ResponseEntity.notFound().build();
    }
}
