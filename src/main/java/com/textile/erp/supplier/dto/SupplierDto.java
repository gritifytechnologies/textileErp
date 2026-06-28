package com.textile.erp.supplier.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SupplierDto {

    private Long          id;
    private Long          tenantId;
    private String        name;
    private String        contactPerson;
    private String        email;
    private String        phone;
    private String        address;
    private String        city;
    private String        country;
    private String        taxNumber;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
