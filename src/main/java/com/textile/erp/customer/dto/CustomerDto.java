package com.textile.erp.customer.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class CustomerDto {

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
    private BigDecimal    creditLimit;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
