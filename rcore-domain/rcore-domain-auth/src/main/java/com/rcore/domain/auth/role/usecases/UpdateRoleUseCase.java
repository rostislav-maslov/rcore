package com.rcore.domain.auth.role.usecases;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.exception.RoleNotFoundException;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.AbstractUpdateUseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

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
        roleEntity.setAccesses(inputValues.getAccesses());

        return SingletonEntityOutputValues.of(repository.save(roleEntity));
    }

    @Data
    public static class InputValues extends CreateRoleUseCase.InputValues {
        private String id;
    }

    private void validate(InputValues inputValues, RoleEntity roleEntity) {

    }

}
