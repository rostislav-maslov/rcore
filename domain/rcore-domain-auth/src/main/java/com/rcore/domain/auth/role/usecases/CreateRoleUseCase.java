package com.rcore.domain.auth.role.usecases;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

import java.util.List;

public class CreateRoleUseCase extends UseCase<CreateRoleUseCase.InputValues, CreateRoleUseCase.OutputValues> {

    private final RoleRepository roleRepository;
    private final RoleIdGenerator roleIdGenerator;

    public CreateRoleUseCase(RoleRepository roleRepository, RoleIdGenerator roleIdGenerator) {
        this.roleRepository = roleRepository;
        this.roleIdGenerator = roleIdGenerator;
    }

    @Override
    public OutputValues execute(InputValues inputValues) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setAccesses(inputValues.getAccesses());
        roleEntity.setAvailableAuthTypes(inputValues.getAvailableAuthTypes());
        roleEntity.setHasBoundlessAccess(inputValues.getHasBoundlessAccess());
        roleEntity.setName(inputValues.getName());
        roleEntity.setId(roleIdGenerator.generate());
        return new OutputValues(roleRepository.save(roleEntity));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String name;
        private final List<String> accesses;
        private final Boolean hasBoundlessAccess;
        private final List<RoleEntity.AuthType> availableAuthTypes;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final RoleEntity roleEntity;
    }
}
