package com.textile.erp.tenant.entity;

import com.textile.erp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tenant", uniqueConstraints = {
        @UniqueConstraint(name = "uk_tenant_subdomain", columnNames = "subdomain"),
        @UniqueConstraint(name = "uk_tenant_email",     columnNames = "email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant extends BaseEntity {

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "subdomain", nullable = false, length = 100)
    private String subdomain;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}