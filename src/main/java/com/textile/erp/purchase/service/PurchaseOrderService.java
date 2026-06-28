package com.textile.erp.purchase.service;

import com.textile.erp.purchase.dto.PurchaseOrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Purchase Order service contract.
 * Business logic (receiving goods, GRN generation, stock movement) will be implemented
 * in a future sprint. This interface exists so modules can depend on the contract now.
 */
public interface PurchaseOrderService {

    PurchaseOrderDto findById(Long id);

    Page<PurchaseOrderDto> findAll(Pageable pageable);
}
