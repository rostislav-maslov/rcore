package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialRoleIsRequiredException;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.exception.RoleNotFoundException;
import com.rcore.domain.auth.role.usecases.FindRoleByIdUseCase;
import com.rcore.domain.commons.usecase.AbstractCreateUseCase;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Builder;
import lombok.Value;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CreateCredentialUseCase extends AbstractCreateUseCase<CredentialEntity, CredentialIdGenerator, CredentialRepository, CreateCredentialUseCase.InputValues> {

    private final FindRoleByIdUseCase findRoleByIdUseCase;

    public CreateCredentialUseCase(CredentialRepository repository, CredentialIdGenerator idGenerator, FindRoleByIdUseCase findRoleByIdUseCase) {
        super(repository, idGenerator);
        this.findRoleByIdUseCase = findRoleByIdUseCase;
    }

    @Override
    public OutputValues<CredentialEntity> execute(InputValues inputValues) {
        validate(inputValues);

        CredentialEntity credentialEntity = new CredentialEntity();

        return AbstractCreateUseCase.OutputValues.of(repository.save(credentialEntity));
    }

    @Value
    @Builder
    public static class InputValues implements UseCase.InputValues {
        protected String username;
        protected String password;
        protected String phone;
        protected String email;
        protected List<CredentialEntity.Role> roles;
        protected CredentialEntity.Status status;
    }

    private void validate(InputValues inputValues) {
        if (inputValues.getRoles() == null || inputValues.getRoles().isEmpty())
            throw new CredentialRoleIsRequiredException();

        List<RoleEntity> roles = new ArrayList<>();

        //Проверка ролей

        for (CredentialEntity.Role role : inputValues.getRoles()) {
            if (role.getRoleId() != null)
                roles.add(findRoleByIdUseCase.execute(AbstractFindByIdUseCase.InputValues.of(role.getRoleId()))
                        .getResult()
                        .orElseThrow(() -> new RoleNotFoundException(role.getRoleId())));
            else if (role.getName() != null)
                roles.add(roleRepository.findByName(role.getName())
                        .orElseThrow(InvalidRoleException::new));
        }

    }
}
