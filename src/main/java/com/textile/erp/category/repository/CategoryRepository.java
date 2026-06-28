package com.textile.erp.category.repository;

import com.textile.erp.category.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByTenantId(Long tenantId, Pageable pageable);

    Optional<Category> findByIdAndTenantId(Long id, Long tenantId);

    List<Category> findAllByTenantIdAndParentIsNull(Long tenantId);

    List<Category> findAllByTenantIdAndParentId(Long tenantId, Long parentId);

    boolean existsByNameAndTenantId(String name, Long tenantId);
}