package com.example.supply_service.mapper;

import org.mapstruct.Mapper;
import com.example.supply_service.dto.SupplierDTO;
import com.example.supply_service.entity.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toDTO(Supplier supplier);

    Supplier toEntity(SupplierDTO dto);

}
