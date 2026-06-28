package com.textile.erp.unit.entity;

import com.textile.erp.common.entity.TenantAwareEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Unit extends TenantAwareEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "abbreviation", nullable = false, length = 20)
    private String abbreviation;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}