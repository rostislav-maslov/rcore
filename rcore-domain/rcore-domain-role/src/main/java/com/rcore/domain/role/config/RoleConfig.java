package com.rcore.domain.role.config;
import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.role.usecases.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleConfig {

    private final RoleRepository roleRepository;
    private final RoleIdGenerator roleIdGenerator;

    public CreateRoleUseCase createRoleUseCase() {
        return new CreateRoleUseCase(roleRepository, roleIdGenerator);
    }

    public FindRoleByIdUseCase findRoleByIdUseCase() {
        return new FindRoleByIdUseCase(roleRepository);
    }

    public FindRoleByNameUseCase findRoleByNameUseCase() {
        return new FindRoleByNameUseCase(roleRepository);
    }

    public FindRolesWithFiltersUseCase findRolesWithFiltersUseCase() {
        return new FindRolesWithFiltersUseCase(roleRepository);
    }

    public UpdateRoleUseCase updateRoleUseCase() {
        return new UpdateRoleUseCase(roleRepository);
    }

}
