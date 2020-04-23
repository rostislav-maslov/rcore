package com.rcore.adapter.domain.role;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.domain.role.config.RoleConfig;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleAdminAdapter {

    private RoleMapper roleMapper = new RoleMapper();
    private final RoleConfig roleConfig;

    public RoleDTO create(RoleDTO role) throws AuthorizationException, AuthenticationException {
        return roleMapper.map(roleConfig.admin
                .createUseCase().create(roleMapper.inverseMap(role)));
    }
}
