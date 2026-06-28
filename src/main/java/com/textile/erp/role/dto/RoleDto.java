package com.textile.erp.role.dto;

import com.textile.erp.role.entity.RoleName;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RoleDto {

    private Long          id;
    private RoleName      name;
    private String        description;
    private LocalDateTime createdAt;
}
