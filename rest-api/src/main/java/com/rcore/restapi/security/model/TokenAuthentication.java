package com.rcore.restapi.security.model;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.domain.role.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;

@Setter
@Getter
public class TokenAuthentication implements Authentication {

    private AccessTokenDTO token;
    private UserDTO user;
    private boolean authenticated;

    public TokenAuthentication(AccessTokenDTO token, boolean authenticated) {
        this.token = token;
        this.authenticated = authenticated;
    }

    public static Authentication ofToken(AccessTokenDTO token) {
        return new TokenAuthentication(token, false);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getRoles().stream()
                .map(Role::getId)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user.getLogin();
    }
}
