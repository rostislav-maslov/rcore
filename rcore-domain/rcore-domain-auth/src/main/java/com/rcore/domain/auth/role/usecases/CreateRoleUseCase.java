package com.rcore.domain.auth.role.usecases;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleIdGenerator;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class CreateRoleUseCase extends AbstractCreateUseCase<RoleEntity, RoleIdGenerator, RoleRepository, CreateRoleUseCase.InputValues> {

    public CreateRoleUseCase(RoleRepository repository, RoleIdGenerator idGenerator) {
        super(repository, idGenerator);
    }

    @Override
    public SingletonEntityOutputValues<RoleEntity> execute(InputValues inputValues) {
        validate(inputValues);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(idGenerator.generate());
        roleEntity.setName(inputValues.getName());
        roleEntity.setHasBoundlessAccess(inputValues.getHasBoundlessAccess());
        roleEntity.setAvailableAuthTypes(inputValues.getAvailableAuthTypes());
        roleEntity.setAvailableUseCases(inputValues.getAccesses());

        return SingletonEntityOutputValues.of(repository.save(roleEntity));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @Data
    public static class InputValues implements UseCase.InputValues {
        private String name;
        private List<String> accesses;
        private Boolean hasBoundlessAccess;
        private List<RoleEntity.AuthType> availableAuthTypes;
    }

    private void validate(InputValues inputValues) {

    }
}
