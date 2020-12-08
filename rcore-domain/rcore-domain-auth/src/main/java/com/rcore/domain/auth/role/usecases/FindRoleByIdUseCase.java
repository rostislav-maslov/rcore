package com.rcore.domain.auth.role.usecases;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindRoleByIdUseCase extends AbstractFindByIdUseCase<String, RoleEntity, RoleRepository> {

    public FindRoleByIdUseCase(RoleRepository repository) {
        super(repository);
    }
}
