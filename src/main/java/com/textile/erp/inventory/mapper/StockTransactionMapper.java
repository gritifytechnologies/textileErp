package com.textile.erp.inventory.mapper;

import com.textile.erp.inventory.dto.StockTransactionDto;
import com.textile.erp.inventory.entity.StockTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StockTransactionMapper {

    @Mapping(target = "productId",     source = "product.id")
    @Mapping(target = "productName",   source = "product.name")
    @Mapping(target = "warehouseId",   source = "warehouse.id")
    @Mapping(target = "warehouseName", source = "warehouse.name")
    @Mapping(target = "createdById",   source = "createdBy.id")
    @Mapping(target = "createdByName", expression = "java(tx.getCreatedBy() != null ? tx.getCreatedBy().getFirstName() + ' ' + tx.getCreatedBy().getLastName() : null)")
    StockTransactionDto toDto(StockTransaction tx);
}
