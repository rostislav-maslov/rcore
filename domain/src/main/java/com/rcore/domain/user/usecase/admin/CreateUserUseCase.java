package com.rcore.domain.user.usecase.admin;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForCreateException;
import com.rcore.domain.phoneNumberFormat.validators.PhoneNumberValidator;
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
    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

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

    public UserEntity create(CreateUserCommand createUserCommand) throws AuthorizationException, TokenExpiredException, AuthenticationException, RoleIsRequiredForUpdateException, PhoneIsRequiredForUpdateException, InvalidEmailForUpdateException, UserAlreadyExistException, UserWithPhoneAlreadyExistForUpdateException, InvalidFirstNameForUpdateException, InvalidLastNameForUpdateException, InvalidRoleForUpdateException, UserWithEmailAlreadyExistForUpdateException, InvalidAccountStatusForUpdateException, InvalidPhoneFormatForCreateException, InvalidFirstNameForCreateException, PhoneIsRequiredForCreateException, InvalidLastNameForCreateException, InvalidRoleForCreateException, InvalidEmailForCreateException, RoleIsRequiredForCreateException, InvalidAccountStatusForCreateException, UserWithEmailAlreadyExistForCreateException, UserWithPhoneAlreadyExistForCreateException, InvalidPasswordForCreateException {
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
        userEntity.setFullName(createUserCommand.getFullName());
        userEntity.setStatus(Optional.ofNullable(createUserCommand.getStatus())
                .orElse(UserStatus.ACTIVE));
        userEntity.setCountryId(createUserCommand.getCountryId());
        userEntity.setLogin(createUserCommand.getLogin());

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    private void validate(CreateUserCommand createUserCommand) throws InvalidFirstNameForUpdateException, InvalidLastNameForUpdateException, InvalidAccountStatusForUpdateException, InvalidRoleForUpdateException, RoleIsRequiredForUpdateException, PhoneIsRequiredForUpdateException, UserWithPhoneAlreadyExistForUpdateException, UserWithEmailAlreadyExistForUpdateException, InvalidEmailForUpdateException, InvalidPhoneFormatForCreateException, InvalidFirstNameForCreateException, InvalidLastNameForCreateException, InvalidAccountStatusForCreateException, InvalidRoleForCreateException, RoleIsRequiredForCreateException, PhoneIsRequiredForCreateException, InvalidEmailForCreateException, UserWithPhoneAlreadyExistForCreateException, UserWithEmailAlreadyExistForCreateException, InvalidPasswordForCreateException {
        if (Objects.nonNull(createUserCommand.getPhone()) && Objects.nonNull(createUserCommand.getPhoneNumberFormat()))
            if (phoneNumberValidator.validatePhone(createUserCommand.getPhone(), createUserCommand.getPhoneNumberFormat()))
                throw new InvalidPhoneFormatForCreateException();

        //Проверка firstName
        if (!StringUtils.hasText(createUserCommand.getFirstName()))
            throw new InvalidFirstNameForCreateException();

        //Проверка lastName
        if (!StringUtils.hasText(createUserCommand.getLastName()))
            throw new InvalidLastNameForCreateException();

        if (Objects.isNull(createUserCommand.getStatus()))
            throw new InvalidAccountStatusForCreateException();

        List<RoleEntity> roles = new ArrayList<>();

        //Проверка ролей
        if (Objects.nonNull(createUserCommand.getRoles())) {
            for (CreateUserCommand.Role role : createUserCommand.getRoles()) {
                if (StringUtils.hasText(role.getId()))
                    roles.add(roleRepository.findById(role.getId())
                            .orElseThrow(InvalidRoleForCreateException::new));
                else if (StringUtils.hasText(role.getName()))
                    roles.add(roleRepository.findByName(role.getName())
                            .orElseThrow(InvalidRoleForCreateException::new));
            }
        }

        if (roles.isEmpty())
            throw new RoleIsRequiredForCreateException();

        //Достаем типы авторизации из ролей
        List<RoleEntity.AuthType> authTypes = roles
                .stream()
                .flatMap(r -> r.getAvailableAuthTypes().stream())
                .collect(Collectors.toList());



        //В зависимости от типов авторизации проверяем обязательные поля
        //Если тип SMS, то phone - обязателен
        if (authTypes.contains(RoleEntity.AuthType.SMS)) {
            if (Objects.isNull(createUserCommand.getPhone()))
                throw new PhoneIsRequiredForCreateException();
        }
        //Если тип EMAIL, то email и password - обязательные поля
        if (authTypes.contains(RoleEntity.AuthType.EMAIL)) {
            if (!StringUtils.hasText(createUserCommand.getEmail()))
                throw new InvalidEmailForCreateException();

            if (!StringUtils.hasText(createUserCommand.getPassword()))
                throw new InvalidPasswordForCreateException();
        }

        if (Objects.nonNull(createUserCommand.getPhone())) {
            if (userRepository.findByPhoneNumber(createUserCommand.getPhone()).isPresent())
                throw new UserWithPhoneAlreadyExistForCreateException();
        }

        if (Objects.nonNull(createUserCommand.getEmail())) {
            if (userRepository.findByEmail(createUserCommand.getEmail()).isPresent())
                throw new UserWithEmailAlreadyExistForCreateException();
        }
    }


}