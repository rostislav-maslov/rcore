package com.rcore.domain.role.usecases;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.port.filters.RoleFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindRolesWithFiltersUseCase extends AbstractFindWithFiltersUseCase<RoleEntity, RoleFilters, RoleRepository> {

    public FindRolesWithFiltersUseCase(RoleRepository repository) {
        super(repository);
    }
}
