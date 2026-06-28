package com.textile.erp.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private long   expiresIn;
    private Long   userId;
    private Long   tenantId;
    private String username;
    private String email;
    private String role;
}