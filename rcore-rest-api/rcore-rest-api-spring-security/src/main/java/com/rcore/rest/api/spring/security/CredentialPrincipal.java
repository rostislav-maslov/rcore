package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.model.CredentialDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class CredentialPrincipal implements UserDetails {

    private String id;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAnonymous;

    public static CredentialPrincipal from(CredentialDetails credentialDetails) {
        return new CredentialPrincipal(
                credentialDetails.getId(),
                credentialDetails.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getCode()))
                        .collect(Collectors.toList()),
                false
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
