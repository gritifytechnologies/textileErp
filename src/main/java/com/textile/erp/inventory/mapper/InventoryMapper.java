package com.textile.erp.inventory.mapper;

import com.textile.erp.inventory.dto.InventoryDto;
import com.textile.erp.inventory.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface InventoryMapper {

    @Mapping(target = "productId",        source = "product.id")
    @Mapping(target = "productName",      source = "product.name")
    @Mapping(target = "productCode",      source = "product.code")
    @Mapping(target = "warehouseId",      source = "warehouse.id")
    @Mapping(target = "warehouseName",    source = "warehouse.name")
    @Mapping(target = "unitAbbreviation", source = "product.unit.abbreviation")
    InventoryDto toDto(Inventory inventory);
}
