package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleViewAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.util.Optional;

public class RoleViewUseCase extends RoleAdminBaseUseCase {

    public RoleViewUseCase(RoleRepository roleRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(roleRepository, new AdminRoleViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<RoleEntity> findById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return roleRepository.findById(id);
    }

    public Optional<RoleEntity> search(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return roleRepository.findById(id);
    }

    public SearchResult<RoleEntity> find(Long size, Long skip) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return roleRepository.find(size, skip);
    }

}