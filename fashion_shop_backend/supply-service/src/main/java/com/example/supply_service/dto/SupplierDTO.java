package com.example.supply_service.dto;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long supplierID;
    private String name;
    private String category;
    private String address;
    private String phone;
    private boolean isActive;
}
