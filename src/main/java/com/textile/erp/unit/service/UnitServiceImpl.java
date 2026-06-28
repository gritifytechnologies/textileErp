package com.textile.erp.unit.service;

import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.security.TenantContext;
import com.textile.erp.unit.dto.UnitDto;
import com.textile.erp.unit.dto.UnitRequest;
import com.textile.erp.unit.entity.Unit;
import com.textile.erp.unit.mapper.UnitMapper;
import com.textile.erp.unit.repository.UnitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper     unitMapper;

    public UnitServiceImpl(UnitRepository unitRepository, UnitMapper unitMapper) {
        this.unitRepository = unitRepository;
        this.unitMapper     = unitMapper;
    }

    @Override
    public UnitDto create(UnitRequest request) {
        Long tenantId = TenantContext.getTenantId();
        if (unitRepository.existsByNameAndTenantId(request.getName(), tenantId)) {
            throw new DuplicateResourceException("Unit", "name", request.getName());
        }
        if (unitRepository.existsByAbbreviationAndTenantId(request.getAbbreviation(), tenantId)) {
            throw new DuplicateResourceException("Unit", "abbreviation", request.getAbbreviation());
        }
        Unit unit = unitMapper.toEntity(request);
        unit.setTenantId(tenantId);
        return unitMapper.toDto(unitRepository.save(unit));
    }

    @Override
    @Transactional(readOnly = true)
    public UnitDto findById(Long id) {
        return unitMapper.toDto(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnitDto> findAll(Pageable pageable) {
        return unitRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(unitMapper::toDto);
    }

    @Override
    public UnitDto update(Long id, UnitRequest request) {
        Unit unit = getOrThrow(id);
        unitMapper.updateEntity(request, unit);
        return unitMapper.toDto(unitRepository.save(unit));
    }

    @Override
    public void deactivate(Long id) {
        Unit unit = getOrThrow(id);
        unit.setActive(false);
        unitRepository.save(unit);
    }

    private Unit getOrThrow(Long id) {
        return unitRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Unit", "id", id));
    }
}
