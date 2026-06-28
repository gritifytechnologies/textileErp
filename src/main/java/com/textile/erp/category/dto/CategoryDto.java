package com.textile.erp.category.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CategoryDto {

    private Long          id;
    private Long          tenantId;
    private String        name;
    private String        description;
    private Long          parentId;
    private String        parentName;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
