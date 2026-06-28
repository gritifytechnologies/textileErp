package com.textile.erp.tenant.mapper;

import com.textile.erp.tenant.dto.TenantDto;
import com.textile.erp.tenant.dto.TenantRequest;
import com.textile.erp.tenant.entity.Tenant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface TenantMapper {

    TenantDto toDto(Tenant tenant);

    Tenant toEntity(TenantRequest request);

    void updateEntity(TenantRequest request, @MappingTarget Tenant tenant);
}