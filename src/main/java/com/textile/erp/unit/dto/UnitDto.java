package com.textile.erp.unit.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UnitDto {

    private Long          id;
    private Long          tenantId;
    private String        name;
    private String        abbreviation;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
