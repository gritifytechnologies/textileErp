package com.textile.erp.security;

import com.textile.erp.user.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {

    private final Long   id;
    private final Long   tenantId;
    private final String username;
    private final String email;
    private final String password;
    private final String role;
    private final boolean active;
    private final Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(Long id, Long tenantId, String username, String email,
                          String password, String role, boolean active) {
        this.id          = id;
        this.tenantId    = tenantId;
        this.username    = username;
        this.email       = email;
        this.password    = password;
        this.role        = role;
        this.active      = active;
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    public static UserPrincipal from(User user) {
        return new UserPrincipal(
                user.getId(),
                user.getTenantId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getName().name(),
                user.isActive()
        );
    }

    /** Lightweight principal used exclusively for token refresh — no DB state needed. */
    public static UserPrincipal forRefresh(Long userId, Long tenantId, String role) {
        return new UserPrincipal(userId, tenantId, null, null, null, role, true);
    }

    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return active; }
}