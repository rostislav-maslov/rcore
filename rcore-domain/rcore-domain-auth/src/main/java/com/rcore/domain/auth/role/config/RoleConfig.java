package com.rcore.domain.auth.role.config;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.auth.role.usecases.CreateRoleUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleConfig {

    private final RoleRepository roleRepository;
    private final RoleIdGenerator roleIdGenerator;

    public CreateRoleUseCase createRoleUseCase() {
        return new CreateRoleUseCase(roleRepository, roleIdGenerator);
    }

}
