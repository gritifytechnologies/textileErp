package com.textile.erp.purchase.mapper;

import com.textile.erp.purchase.dto.PurchaseOrderDto;
import com.textile.erp.purchase.dto.PurchaseOrderItemDto;
import com.textile.erp.purchase.entity.PurchaseOrder;
import com.textile.erp.purchase.entity.PurchaseOrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PurchaseOrderMapper {

    @Mapping(target = "supplierId",   source = "supplier.id")
    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "warehouseId",  source = "warehouse.id")
    @Mapping(target = "warehouseName",source = "warehouse.name")
    PurchaseOrderDto toDto(PurchaseOrder order);

    @Mapping(target = "productId",   source = "product.id")
    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "productCode", source = "product.code")
    PurchaseOrderItemDto toItemDto(PurchaseOrderItem item);
}
