package com.textile.erp.tenant.service;

import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.tenant.dto.TenantDto;
import com.textile.erp.tenant.dto.TenantRequest;
import com.textile.erp.tenant.entity.Tenant;
import com.textile.erp.tenant.mapper.TenantMapper;
import com.textile.erp.tenant.repository.TenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapper     tenantMapper;

    public TenantServiceImpl(TenantRepository tenantRepository, TenantMapper tenantMapper) {
        this.tenantRepository = tenantRepository;
        this.tenantMapper     = tenantMapper;
    }

    @Override
    public TenantDto create(TenantRequest request) {
        if (tenantRepository.existsBySubdomain(request.getSubdomain())) {
            throw new DuplicateResourceException("Tenant", "subdomain", request.getSubdomain());
        }
        if (tenantRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Tenant", "email", request.getEmail());
        }
        Tenant tenant = tenantMapper.toEntity(request);
        Tenant saved  = tenantRepository.save(tenant);
        log.info("Tenant [{}] created with id [{}]", saved.getName(), saved.getId());
        return tenantMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TenantDto findById(Long id) {
        return tenantMapper.toDto(getTenantOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenantDto> findAll(Pageable pageable) {
        return tenantRepository.findAll(pageable).map(tenantMapper::toDto);
    }

    @Override
    public TenantDto update(Long id, TenantRequest request) {
        Tenant tenant = getTenantOrThrow(id);
        tenantMapper.updateEntity(request, tenant);
        return tenantMapper.toDto(tenantRepository.save(tenant));
    }

    @Override
    public void deactivate(Long id) {
        Tenant tenant = getTenantOrThrow(id);
        tenant.setActive(false);
        tenantRepository.save(tenant);
        log.info("Tenant [{}] deactivated", id);
    }

    private Tenant getTenantOrThrow(Long id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant", "id", id));
    }
}
