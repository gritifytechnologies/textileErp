package com.textile.erp.warehouse.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WarehouseDto {

    private Long          id;
    private Long          tenantId;
    private String        name;
    private String        code;
    private String        address;
    private String        managerName;
    private String        phone;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
