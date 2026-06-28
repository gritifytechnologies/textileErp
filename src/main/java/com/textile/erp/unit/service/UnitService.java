package com.textile.erp.unit.service;

import com.textile.erp.unit.dto.UnitDto;
import com.textile.erp.unit.dto.UnitRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UnitService {

    UnitDto create(UnitRequest request);

    UnitDto findById(Long id);

    Page<UnitDto> findAll(Pageable pageable);

    UnitDto update(Long id, UnitRequest request);

    void deactivate(Long id);
}
