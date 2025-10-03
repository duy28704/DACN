package com.example.supply_service.service;

import com.example.supply_service.dto.SupplierDTO;
import com.example.supply_service.entity.Supplier;
import com.example.supply_service.mapper.SupplierMapper;
import com.example.supply_service.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierServiceInterface {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toDTO)  // dùng mapper
                .toList();
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        return supplier.map(supplierMapper::toDTO).orElse(null);
    }

    @Override
    public boolean createSupplier(SupplierDTO supplierDTO) {
        try {
            Supplier supplier = supplierMapper.toEntity(supplierDTO);
            supplierRepository.save(supplier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateSupplier(Long id, SupplierDTO supplierDTO) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            // map lại DTO -> entity (có thể dùng @MappingTarget để update)
            supplier.setName(supplierDTO.getName());
            supplier.setCategory(supplierDTO.getCategory());
            supplier.setAddress(supplierDTO.getAddress());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setActive(supplierDTO.isActive());
            supplierRepository.save(supplier);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deactivateSupplier(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            supplier.setActive(false);
            supplierRepository.save(supplier);
            return true;
        }
        return false;
    }

    @Override
    public boolean activateSupplier(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();
            supplier.setActive(true);
            supplierRepository.save(supplier);
            return true;
        }
        return false;
    }
}
