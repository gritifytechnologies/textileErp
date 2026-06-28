package com.textile.erp.inventory.service;

import com.textile.erp.inventory.dto.InventoryDto;
import com.textile.erp.inventory.dto.StockTransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {

    InventoryDto findByProductAndWarehouse(Long productId, Long warehouseId);

    Page<InventoryDto> findAll(Pageable pageable);

    Page<StockTransactionDto> findTransactions(Long productId, Pageable pageable);
}
