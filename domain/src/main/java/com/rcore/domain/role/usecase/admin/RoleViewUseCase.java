package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleViewAccess;
import com.rcore.domain.role.port.filters.RoleFilters;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.exception.TokenExpiredException;

import java.util.Optional;

public class RoleViewUseCase extends RoleAdminBaseUseCase {

    public RoleViewUseCase(RoleRepository roleRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(roleRepository, new AdminRoleViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<RoleEntity> findById(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return roleRepository.findById(id);
    }

    public Optional<RoleEntity> findByName(String name) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return roleRepository.findByName(name);
    }

    public Optional<RoleEntity> search(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return roleRepository.findById(id);
    }

    public SearchResult<RoleEntity> find(SearchRequest request) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return roleRepository.find(request);
    }

    public SearchResult<RoleEntity> findWithFilters(RoleFilters roleFilters) throws AuthorizationException, TokenExpiredException, AuthenticationException {
        checkAccess();
        return roleRepository.findWithFilters(roleFilters);
    }

}