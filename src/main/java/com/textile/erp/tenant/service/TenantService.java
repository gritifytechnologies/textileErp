package com.textile.erp.tenant.service;

import com.textile.erp.tenant.dto.TenantDto;
import com.textile.erp.tenant.dto.TenantRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TenantService {

    TenantDto create(TenantRequest request);

    TenantDto findById(Long id);

    Page<TenantDto> findAll(Pageable pageable);

    TenantDto update(Long id, TenantRequest request);

    void deactivate(Long id);
}
