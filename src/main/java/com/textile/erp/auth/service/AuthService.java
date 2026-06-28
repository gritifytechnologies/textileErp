package com.textile.erp.auth.service;

import com.textile.erp.auth.dto.LoginRequest;
import com.textile.erp.auth.dto.LoginResponse;
import com.textile.erp.auth.dto.RefreshTokenRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(RefreshTokenRequest request);
}