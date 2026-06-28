package com.textile.erp.tenant.repository;

import com.textile.erp.tenant.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findBySubdomain(String subdomain);

    Optional<Tenant> findByEmail(String email);

    boolean existsBySubdomain(String subdomain);

    boolean existsByEmail(String email);
}