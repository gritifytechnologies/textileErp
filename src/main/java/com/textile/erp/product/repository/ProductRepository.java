package com.textile.erp.product.repository;

import com.textile.erp.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByTenantId(Long tenantId, Pageable pageable);

    Page<Product> findAllByTenantIdAndCategoryId(Long tenantId, Long categoryId, Pageable pageable);

    Optional<Product> findByIdAndTenantId(Long id, Long tenantId);

    boolean existsByCodeAndTenantId(String code, Long tenantId);
}