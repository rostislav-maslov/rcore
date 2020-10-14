package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleCreateAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.TokenExpiredException;


public class RoleCreateUseCase  extends RoleAdminBaseUseCase {
    private final RoleIdGenerator idGenerator;

    public RoleCreateUseCase(RoleRepository roleRepository, RoleIdGenerator idGenerator, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(roleRepository, new AdminRoleCreateAccess(), authorizationByTokenUseCase);
        this.idGenerator = idGenerator;
    }

    public RoleEntity create(RoleEntity roleEntity) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        roleEntity.setId(idGenerator.generate());

        roleEntity = roleRepository.save(roleEntity);

        return roleEntity;
    }


}