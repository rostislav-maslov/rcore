package com.rcore.rest.api.presenter.resources.role;

import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import com.rcore.domain.commons.usecase.UseCaseExecutor;
import com.rcore.rest.api.presenter.resources.role.api.request.CreateRoleRequest;
import com.rcore.rest.api.presenter.resources.role.api.response.RoleResponse;
import com.rcore.rest.api.presenter.resources.role.mappers.CreateRoleInputMapper;
import com.rcore.rest.api.presenter.resources.role.mappers.CreateRoleOutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Component
public class RoleController implements RoleResource {

    private final UseCaseExecutor useCaseExecutor;
    private final CreateRoleUseCase createRoleUseCase;

    @Override
    public CompletableFuture<RoleResponse> create(CreateRoleRequest createRoleRequest) {
        return useCaseExecutor.execute(
                createRoleUseCase,
                CreateRoleInputMapper.map(createRoleRequest),
                CreateRoleOutputMapper::map
        );
    }
}
