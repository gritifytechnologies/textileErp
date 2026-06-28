package com.textile.erp.warehouse.mapper;

import com.textile.erp.warehouse.dto.WarehouseDto;
import com.textile.erp.warehouse.dto.WarehouseRequest;
import com.textile.erp.warehouse.entity.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface WarehouseMapper {

    WarehouseDto toDto(Warehouse warehouse);

    Warehouse toEntity(WarehouseRequest request);

    void updateEntity(WarehouseRequest request, @MappingTarget Warehouse warehouse);
}
