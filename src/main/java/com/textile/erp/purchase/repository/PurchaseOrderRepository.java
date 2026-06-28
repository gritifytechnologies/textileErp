package com.textile.erp.purchase.repository;

import com.textile.erp.purchase.entity.PurchaseOrder;
import com.textile.erp.purchase.entity.PurchaseOrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    Page<PurchaseOrder> findAllByTenantId(Long tenantId, Pageable pageable);

    Page<PurchaseOrder> findAllByTenantIdAndStatus(Long tenantId, PurchaseOrderStatus status, Pageable pageable);

    Optional<PurchaseOrder> findByIdAndTenantId(Long id, Long tenantId);

    boolean existsByOrderNumberAndTenantId(String orderNumber, Long tenantId);
}
