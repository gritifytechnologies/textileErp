package com.textile.erp.warehouse.service;

import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.security.TenantContext;
import com.textile.erp.warehouse.dto.WarehouseDto;
import com.textile.erp.warehouse.dto.WarehouseRequest;
import com.textile.erp.warehouse.entity.Warehouse;
import com.textile.erp.warehouse.mapper.WarehouseMapper;
import com.textile.erp.warehouse.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper     warehouseMapper;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper     = warehouseMapper;
    }

    @Override
    public WarehouseDto create(WarehouseRequest request) {
        Long tenantId = TenantContext.getTenantId();
        if (warehouseRepository.existsByCodeAndTenantId(request.getCode(), tenantId)) {
            throw new DuplicateResourceException("Warehouse", "code", request.getCode());
        }
        Warehouse warehouse = warehouseMapper.toEntity(request);
        warehouse.setTenantId(tenantId);
        return warehouseMapper.toDto(warehouseRepository.save(warehouse));
    }

    @Override
    @Transactional(readOnly = true)
    public WarehouseDto findById(Long id) {
        return warehouseMapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WarehouseDto> findAll(Pageable pageable) {
        return warehouseRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(warehouseMapper::toDto);
    }

    @Override
    public WarehouseDto update(Long id, WarehouseRequest request) {
        Warehouse warehouse = getOrThrow(id);
        warehouseMapper.updateEntity(request, warehouse);
        return warehouseMapper.toDto(warehouseRepository.save(warehouse));
    }

    @Override
    public void deactivate(Long id) {
        Warehouse warehouse = getOrThrow(id);
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
    }

    private Warehouse getOrThrow(Long id) {
        return warehouseRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse", "id", id));
    }
}
