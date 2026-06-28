package com.textile.erp.product.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProductDto {

    private Long          id;
    private Long          tenantId;
    private Long          categoryId;
    private String        categoryName;
    private Long          unitId;
    private String        unitName;
    private String        unitAbbreviation;
    private String        name;
    private String        code;
    private String        description;
    private BigDecimal    buyingPrice;
    private BigDecimal    sellingPrice;
    private BigDecimal    reorderLevel;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
