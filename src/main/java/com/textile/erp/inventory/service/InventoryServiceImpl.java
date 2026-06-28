package com.textile.erp.inventory.service;

import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.inventory.dto.InventoryDto;
import com.textile.erp.inventory.dto.StockTransactionDto;
import com.textile.erp.inventory.mapper.InventoryMapper;
import com.textile.erp.inventory.mapper.StockTransactionMapper;
import com.textile.erp.inventory.repository.InventoryRepository;
import com.textile.erp.inventory.repository.StockTransactionRepository;
import com.textile.erp.security.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository        inventoryRepository;
    private final StockTransactionRepository transactionRepository;
    private final InventoryMapper            inventoryMapper;
    private final StockTransactionMapper     transactionMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository,
                                StockTransactionRepository transactionRepository,
                                InventoryMapper inventoryMapper,
                                StockTransactionMapper transactionMapper) {
        this.inventoryRepository  = inventoryRepository;
        this.transactionRepository = transactionRepository;
        this.inventoryMapper      = inventoryMapper;
        this.transactionMapper    = transactionMapper;
    }

    @Override
    public InventoryDto findByProductAndWarehouse(Long productId, Long warehouseId) {
        return inventoryRepository.findByProductIdAndWarehouseIdAndTenantId(
                        productId, warehouseId, TenantContext.getTenantId())
                .map(inventoryMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory not found for product " + productId + " in warehouse " + warehouseId));
    }

    @Override
    public Page<InventoryDto> findAll(Pageable pageable) {
        return inventoryRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(inventoryMapper::toDto);
    }

    @Override
    public Page<StockTransactionDto> findTransactions(Long productId, Pageable pageable) {
        return transactionRepository.findAllByTenantIdAndProductId(TenantContext.getTenantId(), productId, pageable)
                .map(transactionMapper::toDto);
    }
}
