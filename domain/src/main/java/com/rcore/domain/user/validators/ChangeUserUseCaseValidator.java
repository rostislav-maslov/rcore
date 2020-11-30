package com.rcore.domain.user.validators;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChangeUserUseCaseValidator {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public void validate(UserEntity userEntity, CreateUserCommand createUserCommand) throws InvalidFirstNameException, InvalidLastNameException, InvalidRoleException, RoleIsRequiredException, PhoneIsRequiredException, UserWithPhoneAlreadyExistException, InvalidEmailException, UserAlreadyExistException, UserWithEmailAlreadyExistException, InvalidAccountStatusException {
        //Проверка firstName
        if (!StringUtils.hasText(createUserCommand.getFirstName()) && !StringUtils.hasText(userEntity.getFirstName()))
            throw new InvalidFirstNameException();

        //Проверка lastName
        if (!StringUtils.hasText(createUserCommand.getLastName()) && !StringUtils.hasText(userEntity.getLastName()))
            throw new InvalidLastNameException();

        if (createUserCommand.getStatus() == null && userEntity.getStatus() == null)
            throw new InvalidAccountStatusException();

        List<RoleEntity> roles = userEntity.getRoles().stream().collect(Collectors.toList());

        //Проверка ролей
        if (createUserCommand.getRoles() != null) {
            for (CreateUserCommand.Role role : createUserCommand.getRoles()) {
                if (role.getId() != null)
                    roles.add(roleRepository.findById(role.getId())
                            .orElseThrow(InvalidRoleException::new));
                else if (role.getName() != null)
                    roles.add(roleRepository.findByName(role.getName())
                            .orElseThrow(InvalidRoleException::new));
            }
        }

        if (roles.isEmpty() && userEntity.getRoles().isEmpty())
            throw new RoleIsRequiredException();

        //Достаем типы авторизации из ролей
        List<RoleEntity.AuthType> authTypes = roles
                .stream()
                .flatMap(r -> r.getAvailableAuthTypes().stream())
                .collect(Collectors.toList());

        //В зависимости от типов авторизации проверяем обязательные поля
        //Если тип SMS, то phone - обязателен
        if (authTypes.contains(RoleEntity.AuthType.SMS)) {
            if (createUserCommand.getPhone() == null && userEntity.getPhoneNumber() == null)
                throw new PhoneIsRequiredException();
            Optional<UserEntity> userWithTransferredNumber = userRepository.findByPhoneNumber(createUserCommand.getPhone());
            if (createUserCommand.getPhone() != null && userWithTransferredNumber.isPresent() && !userWithTransferredNumber.get().getId().equals(userEntity.getId()))
                throw new UserWithPhoneAlreadyExistException();
        }
        //Если тип EMAIL, то email и password - обязательные поля
        if (authTypes.contains(RoleEntity.AuthType.EMAIL)) {
            if (!StringUtils.hasText(createUserCommand.getEmail()) && userEntity.getEmail() == null)
                throw new InvalidEmailException();
            Optional<UserEntity> userWithTransferredEmail = userRepository.findByEmail(createUserCommand.getEmail());
            if (createUserCommand.getEmail() != null && userWithTransferredEmail.isPresent() && !userWithTransferredEmail.get().getId().equals(userEntity.getId()))
                throw new UserWithEmailAlreadyExistException();
        }
    }

}
