package com.textile.erp.purchase.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PurchaseOrderItemDto {

    private Long       id;
    private Long       productId;
    private String     productName;
    private String     productCode;
    private BigDecimal orderedQuantity;
    private BigDecimal receivedQuantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
