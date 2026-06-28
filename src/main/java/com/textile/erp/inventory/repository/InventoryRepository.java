package com.textile.erp.inventory.repository;

import com.textile.erp.inventory.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Page<Inventory> findAllByTenantId(Long tenantId, Pageable pageable);

    Optional<Inventory> findByProductIdAndWarehouseIdAndTenantId(Long productId, Long warehouseId, Long tenantId);

    Optional<Inventory> findByIdAndTenantId(Long id, Long tenantId);
}