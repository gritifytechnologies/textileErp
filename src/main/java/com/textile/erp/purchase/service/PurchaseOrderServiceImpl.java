package com.textile.erp.purchase.service;

import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.purchase.dto.PurchaseOrderDto;
import com.textile.erp.purchase.mapper.PurchaseOrderMapper;
import com.textile.erp.purchase.repository.PurchaseOrderRepository;
import com.textile.erp.security.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper     purchaseOrderMapper;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository,
                                    PurchaseOrderMapper purchaseOrderMapper) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper     = purchaseOrderMapper;
    }

    @Override
    public PurchaseOrderDto findById(Long id) {
        return purchaseOrderRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .map(purchaseOrderMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("PurchaseOrder", "id", id));
    }

    @Override
    public Page<PurchaseOrderDto> findAll(Pageable pageable) {
        return purchaseOrderRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(purchaseOrderMapper::toDto);
    }
}
