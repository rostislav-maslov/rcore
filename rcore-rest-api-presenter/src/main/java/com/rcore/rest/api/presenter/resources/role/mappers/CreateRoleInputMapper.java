package com.rcore.rest.api.presenter.resources.role.mappers;

import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.rest.api.presenter.resources.role.api.request.CreateRoleRequest;

public class CreateRoleInputMapper {

    public static CreateRoleUseCase.InputValues map(CreateRoleRequest createRoleRequest) {
        return new CreateRoleUseCase.InputValues(
                createRoleRequest.getName(),
                createRoleRequest.getAccesses(),
                createRoleRequest.getHasBoundlessAccess(),
                createRoleRequest.getAvailableAuthTypes()
        );
    }

}
