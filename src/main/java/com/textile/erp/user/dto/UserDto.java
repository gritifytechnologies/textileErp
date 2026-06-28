package com.textile.erp.user.dto;

import com.textile.erp.role.dto.RoleDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserDto {

    private Long          id;
    private Long          tenantId;
    private RoleDto       role;
    private String        username;
    private String        email;
    private String        firstName;
    private String        lastName;
    private String        phone;
    private boolean       active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
