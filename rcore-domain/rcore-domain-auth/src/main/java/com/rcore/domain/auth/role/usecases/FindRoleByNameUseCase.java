package com.rcore.domain.auth.role.usecases;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.RoleRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonOptionalEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindRoleByNameUseCase extends UseCase<FindRoleByNameUseCase.InputValues, SingletonOptionalEntityOutputValues<RoleEntity>> {

    private final RoleRepository roleRepository;

    @Override
    public SingletonOptionalEntityOutputValues<RoleEntity> execute(InputValues inputValues) {
        return SingletonOptionalEntityOutputValues.of(roleRepository.findByName(inputValues.getName()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String name;
    }
}
