package com.textile.erp.product.entity;

import com.textile.erp.category.entity.Category;
import com.textile.erp.common.entity.TenantAwareEntity;
import com.textile.erp.unit.entity.Unit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "product", uniqueConstraints = {
        @UniqueConstraint(name = "uk_product_code_tenant", columnNames = {"code", "tenant_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends TenantAwareEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "buying_price", nullable = false, precision = 18, scale = 4)
    private BigDecimal buyingPrice;

    @Column(name = "selling_price", nullable = false, precision = 18, scale = 4)
    private BigDecimal sellingPrice;

    @Column(name = "reorder_level", precision = 18, scale = 4)
    private BigDecimal reorderLevel;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}