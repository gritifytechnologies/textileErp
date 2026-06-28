package com.textile.erp.unit.repository;

import com.textile.erp.unit.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Page<Unit> findAllByTenantId(Long tenantId, Pageable pageable);

    Optional<Unit> findByIdAndTenantId(Long id, Long tenantId);

    boolean existsByNameAndTenantId(String name, Long tenantId);

    boolean existsByAbbreviationAndTenantId(String abbreviation, Long tenantId);
}