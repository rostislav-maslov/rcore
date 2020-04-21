package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleDeleteAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class RoleDeleteUseCase  extends RoleAdminBaseUseCase {

    public RoleDeleteUseCase(UserEntity actor, RoleRepository roleRepository) throws AuthorizationException {
        super(actor, roleRepository, new AdminRoleDeleteAccess());
    }

    public Boolean delete(RoleEntity roleEntity){
        roleRepository.delete(roleEntity);

        return true;
    }


}