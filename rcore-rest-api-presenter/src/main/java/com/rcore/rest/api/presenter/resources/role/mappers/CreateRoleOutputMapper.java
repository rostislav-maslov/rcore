package com.rcore.rest.api.presenter.resources.role.mappers;

import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.rest.api.presenter.resources.role.api.response.RoleResponse;

public class CreateRoleOutputMapper {

    public static RoleResponse map(CreateRoleUseCase.OutputValues outputValues) {
        return RoleResponse.from(outputValues.getRoleEntity());
    }

}
