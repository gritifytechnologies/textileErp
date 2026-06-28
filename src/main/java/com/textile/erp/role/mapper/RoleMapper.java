package com.textile.erp.role.mapper;

import com.textile.erp.role.dto.RoleDto;
import com.textile.erp.role.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    RoleDto toDto(Role role);
}
