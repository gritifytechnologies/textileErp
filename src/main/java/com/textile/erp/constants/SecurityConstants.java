package com.textile.erp.constants;

public final class SecurityConstants {

    private SecurityConstants() {}

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX        = "Bearer ";
    public static final String CLAIM_TENANT_ID      = "tenantId";
    public static final String CLAIM_USER_ID        = "userId";
    public static final String CLAIM_ROLE           = "role";

    public static final String[] PUBLIC_ENDPOINTS = {
            "/v1/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/actuator/health",
            "/actuator/info"
    };
}