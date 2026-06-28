package com.textile.erp.supplier.mapper;

import com.textile.erp.supplier.dto.SupplierDto;
import com.textile.erp.supplier.dto.SupplierRequest;
import com.textile.erp.supplier.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface SupplierMapper {

    SupplierDto toDto(Supplier supplier);

    Supplier toEntity(SupplierRequest request);

    void updateEntity(SupplierRequest request, @MappingTarget Supplier supplier);
}
