package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.role.port.RoleRepository;

class RoleAdminBaseUseCase extends AdminUseCase {

    protected final RoleRepository roleRepository;

    public RoleAdminBaseUseCase(RoleRepository roleRepository, Access accessRole, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(accessRole, authorizationByTokenUseCase);
        this.roleRepository = roleRepository;
    }

}
