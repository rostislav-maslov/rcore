package com.rcore.adapter.domain.role;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.role.config.RoleConfig;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class RoleSecureAdapter {

    private RoleMapper roleMapper = new RoleMapper();
    private final RoleConfig roleConfig;

    public SearchResult<RoleDTO> find(SearchRequest request) throws AuthorizationException, AuthenticationException {
        SearchResult<RoleEntity> result = roleConfig.admin.viewUseCase()
                .find(request);

        return SearchResult.withItemsAndCount(
                roleMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }

    public RoleDTO create(RoleDTO role) throws AuthorizationException, AuthenticationException {
        return roleMapper.map(roleConfig.admin
                .createUseCase().create(roleMapper.inverseMap(role)));
    }

    public void addAccess(RoleDTO role, Set<Access> accesses) throws AuthorizationException {
        roleConfig.admin.updateUseCase()
                .addAccesses(roleMapper.inverseMap(role), accesses);
    }

    public Optional<RoleDTO> findById(String id) throws AuthorizationException, AuthenticationException {
        return roleConfig.admin.viewUseCase()
                .findById(id)
                .map(roleMapper::map);
    }

    public Optional<RoleDTO> findByName(String name) throws AuthorizationException, AuthenticationException {
        return roleConfig.admin.viewUseCase()
                .findByName(name)
                .map(roleMapper::map);
    }
}
