package com.rcore.domain.role.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.access.AdminRoleUpdateAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.time.LocalDateTime;

public class RoleUpdateUseCase  extends RoleAdminBaseUseCase {

    public RoleUpdateUseCase(UserEntity actor, RoleRepository roleRepository)throws AuthorizationException {
        super(actor, roleRepository, new AdminRoleUpdateAccess());
    }

    public RoleEntity update(RoleEntity roleEntity){
        roleEntity.setUpdatedAt(LocalDateTime.now());
        return roleRepository.save(roleEntity);
    }


}