package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleUpdateAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.Set;

public class RoleUpdateUseCase  extends RoleAdminBaseUseCase {

    public RoleUpdateUseCase(RoleRepository roleRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase)throws AuthorizationException {
        super(roleRepository, new AdminRoleUpdateAccess(), authorizationByTokenUseCase);
    }

    public RoleEntity update(RoleEntity roleEntity) throws AuthenticationException, AuthorizationException {
        checkAccess();

        roleEntity.setUpdatedAt(LocalDateTime.now());
        return roleRepository.save(roleEntity);
    }

    public RoleEntity addAccesses(RoleEntity role, Set<Access> accesses) {
        role.getAccesses().addAll(accesses);
        return roleRepository.save(role);
    }


}