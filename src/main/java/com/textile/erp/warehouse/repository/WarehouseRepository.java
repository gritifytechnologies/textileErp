package com.textile.erp.warehouse.repository;

import com.textile.erp.warehouse.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Page<Warehouse> findAllByTenantId(Long tenantId, Pageable pageable);

    Optional<Warehouse> findByIdAndTenantId(Long id, Long tenantId);

    boolean existsByCodeAndTenantId(String code, Long tenantId);
}