package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.*;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.exception.RoleNotFoundException;
import com.rcore.domain.auth.role.usecases.FindRoleByIdUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByNameUseCase;
import com.rcore.domain.commons.usecase.AbstractUpdateUseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Обновление учетных данных
 */
public class UpdateCredentialUseCase extends AbstractUpdateUseCase<CredentialEntity, CredentialRepository, UpdateCredentialUseCase.InputValues> {

    private final FindRoleByIdUseCase findRoleByIdUseCase;
    private final FindRoleByNameUseCase findRoleByNameUseCase;
    private final FindCredentialByPhoneUseCase findCredentialByPhoneUseCase;
    private final FindCredentialByUsernameUseCase findCredentialByUsernameUseCase;

    public UpdateCredentialUseCase(CredentialRepository repository, FindRoleByIdUseCase findRoleByIdUseCase, FindRoleByNameUseCase findRoleByNameUseCase, FindCredentialByPhoneUseCase findCredentialByPhoneUseCase, FindCredentialByUsernameUseCase findCredentialByUsernameUseCase) {
        super(repository);
        this.findRoleByIdUseCase = findRoleByIdUseCase;
        this.findRoleByNameUseCase = findRoleByNameUseCase;
        this.findCredentialByPhoneUseCase = findCredentialByPhoneUseCase;
        this.findCredentialByUsernameUseCase = findCredentialByUsernameUseCase;
    }

    @Override
    public SingletonEntityOutputValues<CredentialEntity> execute(InputValues inputValues) {

        CredentialEntity credentialEntity = repository.findById(inputValues.getId())
                .orElseThrow(() -> new CredentialNotFoundException(inputValues.getId()));

        List<RoleEntity> roles = new ArrayList<>();

        //Проверка ролей

        if (inputValues.getRoles() != null)
            for (CreateCredentialUseCase.InputValues.Role role : inputValues.getRoles()) {
                if (role.getRoleId() != null)
                    roles.add(findRoleByIdUseCase.execute(IdInputValues.of(role.getRoleId()))
                            .getEntity()
                            .orElseThrow(() -> new RoleNotFoundException(role.getRoleId())));
                else if (role.getName() != null)
                    roles.add(findRoleByNameUseCase.execute(FindRoleByNameUseCase.InputValues.of(role.getRoleId()))
                            .getEntity()
                            .orElseThrow(() -> new RoleNotFoundException(role.getRoleId())));
            }

        validate(credentialEntity, inputValues, roles);

        credentialEntity.setEmail(inputValues.getEmail());
        credentialEntity.setPhone(inputValues.getPhone());
        credentialEntity.setRoles(roles
                .stream()
                .map(role -> new CredentialEntity.Role(
                        role,
                        findInputRole(inputValues.getRoles(), role)
                                .map(CreateCredentialUseCase.InputValues.Role::getIsBlocked)
                                .orElse(false)))
                .collect(Collectors.toList()));
        credentialEntity.setStatus(inputValues.getStatus());
        credentialEntity.setUsername(inputValues.getUsername());

        return SingletonEntityOutputValues.of(repository.save(credentialEntity));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @SuperBuilder
    @Data
    public static class InputValues extends CreateCredentialUseCase.InputValues {

        private String id;
    }

    private void validate(CredentialEntity credentialEntity, CreateCredentialUseCase.InputValues inputValues, List<RoleEntity> roles) {

        if (roles.isEmpty())
            throw new CredentialRoleIsRequiredException();

        //Достаем типы авторизации из ролей
        List<RoleEntity.AuthType> authTypes = roles
                .stream()
                .flatMap(r -> r.getAvailableAuthTypes().stream())
                .collect(Collectors.toList());

        //В зависимости от типов авторизации проверяем обязательные поля
        //Если тип SMS, то phone - обязателен
        if (authTypes.contains(RoleEntity.AuthType.TWO_FACTOR)) {
            if (inputValues.getPhone() == null)
                throw new CredentialPhoneIsRequiredException();
            Optional<CredentialEntity> credentialWithPhone = findCredentialByPhoneUseCase.execute(FindCredentialByPhoneUseCase.InputValues.of(inputValues.getPhone()))
                    .getCredentialEntity();
            if (credentialWithPhone.isPresent() && !credentialEntity.getId().equals(credentialWithPhone.get().getId()))
                throw new CredentialWithPhoneAlreadyExistException(inputValues.getPhone());
        }
        //Если тип EMAIL, то email и password - обязательные поля
        if (authTypes.contains(RoleEntity.AuthType.PASSWORD)) {
            if (!StringUtils.hasText(inputValues.getUsername()))
                throw new CredentialEmailIsRequiredException();
            Optional<CredentialEntity> credentialWithUsername = findCredentialByUsernameUseCase.execute(FindCredentialByUsernameUseCase.InputValues.of(inputValues.getUsername()))
                    .getEntity();
            if (credentialWithUsername.isPresent() && !credentialEntity.getId().equals(credentialWithUsername.get().getId()))
                throw new CredentialWithUsernameAlreadyExistException(inputValues.getUsername());
        }

        if (inputValues.getStatus() == null)
            throw new CredentialStatusIsRequiredException();
    }

    private Optional<CreateCredentialUseCase.InputValues.Role> findInputRole(List<CreateCredentialUseCase.InputValues.Role> inputRoles, RoleEntity roleEntity) {
        return inputRoles
                .stream()
                .filter(inputRole -> inputRole.getName() == roleEntity.getName() || inputRole.getRoleId() == roleEntity.getId())
                .findFirst();
    }
}
