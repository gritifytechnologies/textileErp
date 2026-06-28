package com.textile.erp.security;

import com.textile.erp.constants.SecurityConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final SecretKey signingKey;
    private final long      expirationMs;
    private final long      refreshExpirationMs;

    public JwtTokenProvider(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long expirationMs,
            @Value("${app.jwt.refresh-expiration-ms}") long refreshExpirationMs) {
        this.signingKey          = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationMs        = expirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
    }

    public String generateToken(UserPrincipal principal) {
        return buildToken(principal, expirationMs);
    }

    public String generateRefreshToken(UserPrincipal principal) {
        return buildToken(principal, refreshExpirationMs);
    }

    private String buildToken(UserPrincipal principal, long expiry) {
        Date now    = new Date();
        Date expDate = new Date(now.getTime() + expiry);

        return Jwts.builder()
                .subject(String.valueOf(principal.getId()))
                .claim(SecurityConstants.CLAIM_USER_ID,   principal.getId())
                .claim(SecurityConstants.CLAIM_TENANT_ID, principal.getTenantId())
                .claim(SecurityConstants.CLAIM_ROLE,      principal.getRole())
                .issuedAt(now)
                .expiration(expDate)
                .signWith(signingKey)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get(SecurityConstants.CLAIM_USER_ID, Long.class);
    }

    public Long getTenantIdFromToken(String token) {
        return getClaims(token).get(SecurityConstants.CLAIM_TENANT_ID, Long.class);
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get(SecurityConstants.CLAIM_ROLE, String.class);
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.warn("JWT token expired: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.warn("JWT token unsupported: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.warn("JWT token malformed: {}", ex.getMessage());
        } catch (SecurityException ex) {
            log.warn("JWT signature invalid: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.warn("JWT token empty/null: {}", ex.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token) {
        return parseClaims(token).getPayload();
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token);
    }
}