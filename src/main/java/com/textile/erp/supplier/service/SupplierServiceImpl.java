package com.textile.erp.supplier.service;

import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.security.TenantContext;
import com.textile.erp.supplier.dto.SupplierDto;
import com.textile.erp.supplier.dto.SupplierRequest;
import com.textile.erp.supplier.entity.Supplier;
import com.textile.erp.supplier.mapper.SupplierMapper;
import com.textile.erp.supplier.repository.SupplierRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper     supplierMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper     = supplierMapper;
    }

    @Override
    public SupplierDto create(SupplierRequest request) {
        Long tenantId = TenantContext.getTenantId();
        if (request.getEmail() != null && supplierRepository.existsByEmailAndTenantId(request.getEmail(), tenantId)) {
            throw new DuplicateResourceException("Supplier", "email", request.getEmail());
        }
        Supplier supplier = supplierMapper.toEntity(request);
        supplier.setTenantId(tenantId);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    @Transactional(readOnly = true)
    public SupplierDto findById(Long id) {
        return supplierMapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SupplierDto> findAll(Pageable pageable) {
        return supplierRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(supplierMapper::toDto);
    }

    @Override
    public SupplierDto update(Long id, SupplierRequest request) {
        Supplier supplier = getOrThrow(id);
        supplierMapper.updateEntity(request, supplier);
        return supplierMapper.toDto(supplierRepository.save(supplier));
    }

    @Override
    public void deactivate(Long id) {
        Supplier supplier = getOrThrow(id);
        supplier.setActive(false);
        supplierRepository.save(supplier);
    }

    private Supplier getOrThrow(Long id) {
        return supplierRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", id));
    }
}
