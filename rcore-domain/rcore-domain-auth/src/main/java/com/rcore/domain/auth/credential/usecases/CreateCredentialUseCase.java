package com.rcore.domain.auth.credential.usecases;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.*;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.exception.RoleNotFoundException;
import com.rcore.domain.auth.role.usecases.FindRoleByIdUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByNameUseCase;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreateCredentialUseCase extends AbstractCreateUseCase<CredentialEntity, CredentialIdGenerator, CredentialRepository, CreateCredentialUseCase.InputValues> {

    private final FindRoleByIdUseCase findRoleByIdUseCase;
    private final FindRoleByNameUseCase findRoleByNameUseCase;
    private final FindCredentialByPhoneUseCase findCredentialByPhoneUseCase;
    private final FindCredentialByEmailUseCase findCredentialByEmailUseCase;
    private final FindCredentialByUsernameUseCase findCredentialByUsernameUseCase;
    private final PasswordCryptographer passwordCryptographer;

    public CreateCredentialUseCase(CredentialRepository repository, CredentialIdGenerator idGenerator, FindRoleByIdUseCase findRoleByIdUseCase, FindRoleByNameUseCase findRoleByNameUseCase, FindCredentialByPhoneUseCase findCredentialByPhoneUseCase, FindCredentialByEmailUseCase findCredentialByEmailUseCase, FindCredentialByUsernameUseCase findCredentialByUsernameUseCase, PasswordCryptographer passwordCryptographer) {
        super(repository, idGenerator);
        this.findRoleByIdUseCase = findRoleByIdUseCase;
        this.findRoleByNameUseCase = findRoleByNameUseCase;
        this.findCredentialByPhoneUseCase = findCredentialByPhoneUseCase;
        this.findCredentialByEmailUseCase = findCredentialByEmailUseCase;
        this.findCredentialByUsernameUseCase = findCredentialByUsernameUseCase;
        this.passwordCryptographer = passwordCryptographer;
    }

    @Override
    public OutputValues<CredentialEntity> execute(InputValues inputValues) {

        List<RoleEntity> roles = new ArrayList<>();

        //Проверка ролей

        if (inputValues.getRoles() != null)
            for (InputValues.Role role : inputValues.getRoles()) {
                if (role.getRoleId() != null)
                    roles.add(findRoleByIdUseCase.execute(AbstractFindByIdUseCase.InputValues.of(role.getRoleId()))
                            .getResult()
                            .orElseThrow(() -> new RoleNotFoundException(role.getRoleId())));
                else if (role.getName() != null)
                    roles.add(findRoleByNameUseCase.execute(FindRoleByNameUseCase.InputValues.of(role.getRoleId()))
                            .getResult()
                            .orElseThrow(() -> new RoleNotFoundException(role.getRoleId())));
            }

        validate(inputValues, roles);

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setId(idGenerator.generate());
        credentialEntity.setEmail(inputValues.getEmail());
        credentialEntity.setPassword(passwordCryptographer.encrypt(inputValues.getPassword(), credentialEntity.getId()));
        credentialEntity.setPhone(inputValues.getPhone());
        credentialEntity.setRoles(inputValues.getRoles()
                .stream()
                .map(role -> new CredentialEntity.Role(role.getRoleId(), role.getIsBlocked()))
                .collect(Collectors.toList()));
        credentialEntity.setStatus(inputValues.getStatus());
        credentialEntity.setUsername(inputValues.getUsername());

        return AbstractCreateUseCase.OutputValues.of(repository.save(credentialEntity));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        protected String username;
        protected String password;
        protected String phone;
        protected String email;
        protected List<Role> roles;
        protected CredentialEntity.Status status;

        @AllArgsConstructor
        @NoArgsConstructor
        @Builder
        @Data
        public static class Role {
            private String roleId;
            private String name;
            private Boolean isBlocked;
        }
    }

    private void validate(InputValues inputValues, List<RoleEntity> roles) {

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
            if (credentialWithPhone.isPresent())
                throw new CredentialWithPhoneAlreadyExistException(inputValues.getPhone());
        }
        //Если тип EMAIL, то email и password - обязательные поля
        if (authTypes.contains(RoleEntity.AuthType.PASSWORD)) {
            if (!StringUtils.hasText(inputValues.getUsername()))
                throw new CredentialEmailIsRequiredException();
            Optional<CredentialEntity> credentialWithUsername = findCredentialByUsernameUseCase.execute(FindCredentialByUsernameUseCase.InputValues.of(inputValues.getUsername()))
                    .getResult();
            if (credentialWithUsername.isPresent())
                throw new CredentialWithUsernameAlreadyExistException(inputValues.getUsername());
        }

        if (inputValues.getStatus() == null)
            throw new CredentialStatusIsRequiredException();
    }
}
