package com.example.supply_service.service;

import java.util.List;

import com.example.supply_service.dto.SupplierDTO;

public interface SupplierServiceInterface {
    List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(Long id);

    boolean createSupplier(SupplierDTO supplierDTO);

    boolean updateSupplier(Long id, SupplierDTO supplierDTO);

    boolean deleteSupplier(Long id);

    boolean deactivateSupplier(Long id);

    boolean activateSupplier(Long id);
}
