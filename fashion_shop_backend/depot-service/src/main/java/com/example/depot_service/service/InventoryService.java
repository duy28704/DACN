package com.example.depot_service.service;
import com.example.depot_service.entity.Inventory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.depot_service.repository.DepotRepository ;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
    

public class InventoryService implements DepotInterface {

    private final DepotRepository inventoryRepository;

    /**
     * Nhập kho (tăng số lượng hoặc thêm mới)
     */
    @Transactional
    @Override
    public boolean addStock(String productId, String color, String size, int quantity) {
        Optional<Inventory> existing = inventoryRepository
                .findByProductIdAndColorAndSize(productId, color, size);

        if (existing.isPresent()) {
            Inventory inv = existing.get();
            inv.setQuantity(inv.getQuantity() + quantity);
            inventoryRepository.save(inv);
            
        } else {
            Inventory inv = new Inventory();
            inv.setProductId(productId);
            inv.setColor(color);
            inv.setSize(size);
            inv.setQuantity(quantity);
            inventoryRepository.save(inv);
          
        }
        return true;
    }

    /**
     * Xuất kho (giảm số lượng)
     */
    @Transactional
    public boolean deductStock(String productId, String color, String size, int quantity) {
        Optional<Inventory> existing = inventoryRepository
                .findByProductIdAndColorAndSize(productId, color, size);

        if (existing.isPresent()) {
            Inventory inv = existing.get();
            if (inv.getQuantity() >= quantity) {
                inv.setQuantity(inv.getQuantity() - quantity);
                inventoryRepository.save(inv);
                return true;
            } else {
                return false; // không đủ hàng
            }
        }
        return false; // không tìm thấy sản phẩm
    }

    /**
     * Kiểm tra tồn kho theo productId + color + size
     */
    public int getStock(String productId, String color, String size) {
        return inventoryRepository
                .findByProductIdAndColorAndSize(productId, color, size)
                .map(Inventory::getQuantity)
                .orElse(0);
    }

    /**
     * Lấy toàn bộ tồn kho
     */
    public List<Inventory> getAllStock() {
        return inventoryRepository.findAll();
    }

    public boolean deleteStock(Long id) {
        inventoryRepository.deleteById(id);
        return true;
    }
}


