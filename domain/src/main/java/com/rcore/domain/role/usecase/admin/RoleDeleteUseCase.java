package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleDeleteAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;

public class RoleDeleteUseCase  extends RoleAdminBaseUseCase {

    public RoleDeleteUseCase(RoleRepository roleRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(roleRepository, new AdminRoleDeleteAccess(), authorizationByTokenUseCase);
    }

    public Boolean delete(RoleEntity roleEntity) throws AuthenticationException, AuthorizationException {
        checkAccess();

        roleRepository.delete(roleEntity);

        return true;
    }


}