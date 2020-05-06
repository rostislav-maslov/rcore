package com.rcore.adapter.domain.role;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.role.config.RoleConfig;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleSecureViewAdapter {
    private final RoleConfig roleConfig;
    private final RoleMapper roleMapper = new RoleMapper();

    public SearchResult<RoleDTO> find(SearchRequest request) throws AuthorizationException, AuthenticationException {
        SearchResult<RoleEntity> result = roleConfig.admin
                .viewUseCase().find(request);

        return SearchResult.withItemsAndCount(
                roleMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }
}
