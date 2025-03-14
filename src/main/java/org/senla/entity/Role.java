package org.senla.entity;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    VIEWER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
