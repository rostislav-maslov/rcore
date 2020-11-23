package com.rcore.domain.user.validators;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ChangeUserUseCaseValidator {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public void validate(CreateUserCommand createUserCommand) throws InvalidFirstNameException, InvalidLastNameException, InvalidRoleException, RoleIsRequiredException, PhoneIsRequiredException, UserWithPhoneAlreadyExistException, InvalidEmailException, UserAlreadyExistException, UserWithEmailAlreadyExistException, InvalidAccountStatusException {
        //Проверка firstName
        if (createUserCommand.getFirstName() == null || createUserCommand.getFirstName().length() == 0)
            throw new InvalidFirstNameException();

        //Проверка lastName
        if (createUserCommand.getLastName() == null || createUserCommand.getLastName().length() == 0)
            throw new InvalidLastNameException();

        if (createUserCommand.getStatus() == null)
            throw new InvalidAccountStatusException();

        List<RoleEntity> roles = new ArrayList<>();

        //Проверка ролей
        if (createUserCommand.getRoles() != null) {
            for (CreateUserCommand.Role role : createUserCommand.getRoles()) {
                    if (role.getId()!= null)
                        roles.add(roleRepository.findById(role.getId())
                                .orElseThrow(InvalidRoleException::new));
                    else if (role.getName() != null)
                        roles.add(roleRepository.findByName(role.getName())
                                .orElseThrow(InvalidRoleException::new));
            }
        }

        if (roles.isEmpty())
            throw new RoleIsRequiredException();

        //Достаем типы авторизации из ролей
        List<RoleEntity.AuthType> authTypes = roles
                .stream()
                .flatMap(r -> r.getAvailableAuthTypes().stream())
                .collect(Collectors.toList());

        //В зависимости от типов авторизации проверяем обязательные поля
        //Если тип SMS, то phone - обязателен
        if (authTypes.contains(RoleEntity.AuthType.SMS)) {
            if (createUserCommand.getPhone() == null)
                throw new PhoneIsRequiredException();

            if (userRepository.findByPhoneNumber(createUserCommand.getPhone()).isPresent())
                throw new UserWithPhoneAlreadyExistException();
        }
        //Если тип EMAIL, то email и password - обязательные поля
        else if (authTypes.contains(RoleEntity.AuthType.EMAIL)) {
            if (createUserCommand.getEmail() == null || createUserCommand.getEmail().length() == 0)
                throw new InvalidEmailException();

            if (userRepository.findByEmail(createUserCommand.getEmail()).isPresent())
                throw new UserWithEmailAlreadyExistException();
        }
    }

}
