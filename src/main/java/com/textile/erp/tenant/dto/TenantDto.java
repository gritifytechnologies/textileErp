package com.textile.erp.tenant.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TenantDto {

    private Long          id;
    private String        name;
    private String        subdomain;
    private String        email;
    private String        phone;
    private String        address;
    private String        logoUrl;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}