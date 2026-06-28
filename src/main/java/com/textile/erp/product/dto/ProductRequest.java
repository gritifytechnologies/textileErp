package com.textile.erp.product.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class ProductRequest {

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    @NotNull(message = "Unit ID is required")
    private Long unitId;

    @NotBlank(message = "Product name is required")
    @Size(max = 200)
    private String name;

    @NotBlank(message = "Product code (SKU) is required")
    @Size(max = 100)
    private String code;

    @Size(max = 1000)
    private String description;

    @NotNull(message = "Buying price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Buying price must be positive")
    private BigDecimal buyingPrice;

    @NotNull(message = "Selling price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Selling price must be positive")
    private BigDecimal sellingPrice;

    @DecimalMin(value = "0.0", message = "Reorder level must be non-negative")
    private BigDecimal reorderLevel;
}
