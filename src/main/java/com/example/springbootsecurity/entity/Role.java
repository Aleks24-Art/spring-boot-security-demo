package com.example.springbootsecurity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(Set.of(Permissions.DEVELOPERS_READ, Permissions.DEVELOPERS_WRITE)),
    GUEST(Set.of(Permissions.DEVELOPERS_READ));

    private final Set<Permissions> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
