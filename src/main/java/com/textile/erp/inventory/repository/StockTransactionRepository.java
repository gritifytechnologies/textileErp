package com.textile.erp.inventory.repository;

import com.textile.erp.inventory.entity.StockTransaction;
import com.textile.erp.inventory.entity.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {

    Page<StockTransaction> findAllByTenantId(Long tenantId, Pageable pageable);

    Page<StockTransaction> findAllByTenantIdAndProductId(Long tenantId, Long productId, Pageable pageable);

    Page<StockTransaction> findAllByTenantIdAndWarehouseId(Long tenantId, Long warehouseId, Pageable pageable);

    Page<StockTransaction> findAllByTenantIdAndTransactionType(Long tenantId, TransactionType type, Pageable pageable);

    Page<StockTransaction> findAllByTenantIdAndReferenceTypeAndReferenceId(
            Long tenantId, String referenceType, Long referenceId, Pageable pageable);
}