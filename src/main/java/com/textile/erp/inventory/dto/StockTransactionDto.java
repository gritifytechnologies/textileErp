package com.textile.erp.inventory.dto;

import com.textile.erp.inventory.entity.TransactionType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class StockTransactionDto {

    private Long            id;
    private Long            tenantId;
    private Long            productId;
    private String          productName;
    private Long            warehouseId;
    private String          warehouseName;
    private TransactionType transactionType;
    private BigDecimal      quantity;
    private String          referenceType;
    private Long            referenceId;
    private String          notes;
    private Long            createdById;
    private String          createdByName;
    private LocalDateTime   createdAt;
}
