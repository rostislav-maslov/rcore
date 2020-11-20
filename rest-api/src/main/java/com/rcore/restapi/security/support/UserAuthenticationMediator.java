package com.rcore.restapi.security.support;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserAuthenticationMediator implements AuthenticationMediator<UserDTO> {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public UserDTO getUser() {
        return (UserDTO)  getAuthentication().getPrincipal();
    }

    @Override
    public AccessTokenDTO getAccessToken() {
        return (AccessTokenDTO) getAuthentication().getDetails();
    }
}
