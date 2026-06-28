package com.textile.erp.warehouse.service;

import com.textile.erp.warehouse.dto.WarehouseDto;
import com.textile.erp.warehouse.dto.WarehouseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WarehouseService {

    WarehouseDto create(WarehouseRequest request);

    WarehouseDto findById(Long id);

    Page<WarehouseDto> findAll(Pageable pageable);

    WarehouseDto update(Long id, WarehouseRequest request);

    void deactivate(Long id);
}
