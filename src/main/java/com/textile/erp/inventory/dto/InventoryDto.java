package com.textile.erp.inventory.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class InventoryDto {

    private Long          id;
    private Long          tenantId;
    private Long          productId;
    private String        productName;
    private String        productCode;
    private Long          warehouseId;
    private String        warehouseName;
    private BigDecimal    quantity;
    private String        unitAbbreviation;
    private LocalDateTime updatedAt;
}
