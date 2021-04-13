package com.rcore.domain.auth.role.usecases;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.role.port.filters.RoleFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindRolesWithFiltersUseCase extends AbstractFindWithFiltersUseCase<RoleEntity, RoleFilters, RoleRepository> {

    public FindRolesWithFiltersUseCase(RoleRepository repository) {
        super(repository);
    }
}
