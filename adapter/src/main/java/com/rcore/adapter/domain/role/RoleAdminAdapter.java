package com.rcore.adapter.domain.role;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.role.config.RoleConfig;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleAdminAdapter {

    private RoleMapper roleMapper = new RoleMapper();
    private UserMapper userMapper = new UserMapper(roleMapper);
    private final RoleConfig roleConfig;

    public RoleDTO create(UserDTO actor, RoleDTO role) throws AuthorizationException {
        return roleMapper.map(roleConfig.admin
                .createUseCase(userMapper.inverseMap(actor)).create(roleMapper.inverseMap(role)));
    }
}
