package com.rcore.domain.role.usecases;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.exception.RoleNotFoundException;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.commons.usecase.AbstractUpdateUseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class UpdateRoleUseCase extends AbstractUpdateUseCase<RoleEntity, RoleRepository, UpdateRoleUseCase.InputValues> {

    public UpdateRoleUseCase(RoleRepository repository) {
        super(repository);
    }

    @Override
    public SingletonEntityOutputValues<RoleEntity> execute(InputValues inputValues) {
        RoleEntity roleEntity = repository.findById(inputValues.getId())
                .orElseThrow(() -> new RoleNotFoundException(inputValues.getId()));

        validate(inputValues, roleEntity);

        roleEntity.setName(inputValues.getName());
        roleEntity.setHasBoundlessAccess(inputValues.getHasBoundlessAccess());
        roleEntity.setAvailableAuthTypes(inputValues.getAvailableAuthTypes());
        roleEntity.setAvailableUseCases(inputValues.getAccesses());

        return SingletonEntityOutputValues.of(repository.save(roleEntity));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Data
    public static class InputValues extends CreateRoleUseCase.InputValues {
        private String id;
    }

    private void validate(InputValues inputValues, RoleEntity roleEntity) {

    }

}
