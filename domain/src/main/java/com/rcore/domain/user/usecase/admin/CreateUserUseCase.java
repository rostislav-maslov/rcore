package com.rcore.domain.user.usecase.admin;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserCreateAccess;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import com.rcore.domain.user.validators.ChangeUserUseCaseValidator;

import java.util.*;
import java.util.stream.Collectors;

public class CreateUserUseCase extends AdminBaseUseCase {
    private final PasswordGenerator passwordGenerator;
    private final UserIdGenerator userIdGenerator;
    private final RoleRepository roleRepository;
    private final ChangeUserUseCaseValidator changeUserUseCaseValidator;

    public CreateUserUseCase(UserRepository userRepository, PasswordGenerator passwordGenerator, UserIdGenerator userIdGenerator, AuthorizationByTokenUseCase authorizationByTokenUseCase, RoleRepository roleRepository) {
        super(userRepository, new AdminUserCreateAccess(), authorizationByTokenUseCase);
        this.passwordGenerator = passwordGenerator;
        this.userIdGenerator = userIdGenerator;
        this.roleRepository = roleRepository;
        this.changeUserUseCaseValidator = new ChangeUserUseCaseValidator(roleRepository, userRepository);
    }

    public UserEntity createByEmail(String email, String password, List<String> roleIds) throws UserAlreadyExistException, AuthenticationException, AuthorizationException, TokenExpiredException {
        return createByEmail(
                email,
                password,
                roleIds
                        .stream()
                        .map(roleId -> roleRepository.findById(roleId).get())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet())
        );
    }

    public UserEntity createByEmail(String email, String password, Set<RoleEntity> roles) throws AuthenticationException, AuthorizationException, UserAlreadyExistException, TokenExpiredException {
        checkAccess();

        Optional<UserEntity> userByEmail = userRepository.findByEmail(email.toLowerCase());
        if (userByEmail.isPresent())
            throw new UserAlreadyExistException();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userIdGenerator.generate());
        userEntity.setEmail(email.toLowerCase());
        userEntity.setRoles(roles);
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), password));

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    public UserEntity create(CreateUserCommand createUserCommand) throws AuthorizationException, TokenExpiredException, AuthenticationException, RoleIsRequiredException, PhoneIsRequiredException, InvalidEmailException, UserAlreadyExistException, UserWithPhoneAlreadyExistException, InvalidFirstNameException, InvalidLastNameException, InvalidRoleException, UserWithEmailAlreadyExistException, InvalidAccountStatusException {
        checkAccess();

        Set<RoleEntity> roles = createUserCommand.getRoles()
                .stream()
                .map(role -> {
                    if (role.getId() != null)
                        return roleRepository.findById(role.getId());
                    else if (role.getName() != null)
                        return roleRepository.findByName(role.getName());

                    return Optional.<RoleEntity>empty();
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity();

        validate(createUserCommand);

        userEntity.setId(userIdGenerator.generate());
        userEntity.setEmail(createUserCommand.getEmail());
        userEntity.setRoles(roles);
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), createUserCommand.getPassword()));
        userEntity.setPhoneNumber(createUserCommand.getPhone());
        userEntity.setProfileImageId(createUserCommand.getProfileImageId());
        userEntity.setFirstName(createUserCommand.getFirstName());
        userEntity.setLastName(createUserCommand.getLastName());
        userEntity.setSecondName(createUserCommand.getSecondName());
        userEntity.setFullName(userEntity.getFullName());
        userEntity.setStatus(Optional.ofNullable(createUserCommand.getStatus())
                .orElse(UserStatus.ACTIVE));
        userEntity.setCountryId(createUserCommand.getCountryId());
        userEntity.setLogin(createUserCommand.getLogin());

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    private void validate(CreateUserCommand createUserCommand) throws InvalidFirstNameException, InvalidLastNameException, InvalidAccountStatusException, InvalidRoleException, RoleIsRequiredException, PhoneIsRequiredException, UserWithPhoneAlreadyExistException, UserWithEmailAlreadyExistException, InvalidEmailException {
        //Проверка firstName
        if (!StringUtils.hasText(createUserCommand.getFirstName()))
            throw new InvalidFirstNameException();

        //Проверка lastName
        if (!StringUtils.hasText(createUserCommand.getLastName()))
            throw new InvalidLastNameException();

        if (createUserCommand.getStatus() == null)
            throw new InvalidAccountStatusException();

        List<RoleEntity> roles = new ArrayList<>();

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
        }
        //Если тип EMAIL, то email и password - обязательные поля
        if (authTypes.contains(RoleEntity.AuthType.EMAIL)) {
            if (!StringUtils.hasText(createUserCommand.getEmail()))
                throw new InvalidEmailException();
        }

        if (createUserCommand.getPhone() != null) {
            if (userRepository.findByPhoneNumber(createUserCommand.getPhone()).isPresent())
                throw new UserWithPhoneAlreadyExistException();
        }

        if (createUserCommand.getEmail() != null) {
            if (userRepository.findByEmail(createUserCommand.getEmail()).isPresent())
                throw new UserWithEmailAlreadyExistException();
        }
    }


}