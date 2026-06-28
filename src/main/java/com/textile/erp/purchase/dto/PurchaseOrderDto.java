package com.textile.erp.purchase.dto;

import com.textile.erp.purchase.entity.PurchaseOrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PurchaseOrderDto {

    private Long                    id;
    private Long                    tenantId;
    private String                  orderNumber;
    private Long                    supplierId;
    private String                  supplierName;
    private Long                    warehouseId;
    private String                  warehouseName;
    private PurchaseOrderStatus     status;
    private LocalDate               orderDate;
    private LocalDate               expectedDeliveryDate;
    private BigDecimal              totalAmount;
    private String                  notes;
    private List<PurchaseOrderItemDto> items;
    private LocalDateTime           createdAt;
    private LocalDateTime           updatedAt;
}
