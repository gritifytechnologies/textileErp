package com.textile.erp.security;

import org.slf4j.MDC;

/**
 * Thread-local holder for the current tenant context.
 * Populated by JwtAuthenticationFilter on every authenticated request.
 * Must be cleared at the end of each request to avoid thread-pool leakage.
 */
public final class TenantContext {

    private static final ThreadLocal<Long>   TENANT_ID = new ThreadLocal<>();
    private static final ThreadLocal<Long>   USER_ID   = new ThreadLocal<>();
    private static final ThreadLocal<String> ROLE      = new ThreadLocal<>();

    private TenantContext() {}

    public static void set(Long tenantId, Long userId, String role) {
        TENANT_ID.set(tenantId);
        USER_ID.set(userId);
        ROLE.set(role);
        MDC.put("tenantId", String.valueOf(tenantId));
    }

    public static Long getTenantId() {
        return TENANT_ID.get();
    }

    public static Long getUserId() {
        return USER_ID.get();
    }

    public static String getRole() {
        return ROLE.get();
    }

    public static void clear() {
        TENANT_ID.remove();
        USER_ID.remove();
        ROLE.remove();
        MDC.remove("tenantId");
    }
}