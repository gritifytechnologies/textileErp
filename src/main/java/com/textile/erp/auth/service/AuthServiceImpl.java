package com.textile.erp.auth.service;

import com.textile.erp.auth.dto.LoginRequest;
import com.textile.erp.auth.dto.LoginResponse;
import com.textile.erp.auth.dto.RefreshTokenRequest;
import com.textile.erp.exception.UnauthorizedException;
import com.textile.erp.security.JwtTokenProvider;
import com.textile.erp.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider      jwtTokenProvider;
    private final long                  expirationMs;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           @Value("${app.jwt.expiration-ms}") long expirationMs) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider      = jwtTokenProvider;
        this.expirationMs          = expirationMs;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        log.info("User [{}] from tenant [{}] authenticated successfully", principal.getUsername(), principal.getTenantId());

        return buildLoginResponse(principal);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String token = request.getRefreshToken();

        if (!jwtTokenProvider.validateToken(token)) {
            throw new UnauthorizedException("Invalid or expired refresh token");
        }

        Long   userId   = jwtTokenProvider.getUserIdFromToken(token);
        Long   tenantId = jwtTokenProvider.getTenantIdFromToken(token);
        String role     = jwtTokenProvider.getRoleFromToken(token);

        // Build a lightweight principal for token generation (no DB hit needed on refresh)
        UserPrincipal principal = UserPrincipal.forRefresh(userId, tenantId, role);

        log.info("Token refreshed for userId [{}] tenantId [{}]", userId, tenantId);

        return buildLoginResponse(principal);
    }

    private LoginResponse buildLoginResponse(UserPrincipal principal) {
        String accessToken  = jwtTokenProvider.generateToken(principal);
        String refreshToken = jwtTokenProvider.generateRefreshToken(principal);

        return LoginResponse.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(expirationMs / 1000)
                .userId(principal.getId())
                .tenantId(principal.getTenantId())
                .username(principal.getUsername())
                .email(principal.getEmail())
                .role(principal.getRole())
                .build();
    }
}