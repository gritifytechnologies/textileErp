package com.textile.erp.supplier.service;

import com.textile.erp.supplier.dto.SupplierDto;
import com.textile.erp.supplier.dto.SupplierRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    SupplierDto create(SupplierRequest request);

    SupplierDto findById(Long id);

    Page<SupplierDto> findAll(Pageable pageable);

    SupplierDto update(Long id, SupplierRequest request);

    void deactivate(Long id);
}
