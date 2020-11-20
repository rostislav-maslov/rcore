package com.rcore.domain.user.usecase.admin;

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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateUserUseCase extends AdminBaseUseCase {
    private final PasswordGenerator passwordGenerator;
    private final UserIdGenerator userIdGenerator;
    private final RoleRepository roleRepository;

    public CreateUserUseCase(UserRepository userRepository, PasswordGenerator passwordGenerator, UserIdGenerator userIdGenerator, AuthorizationByTokenUseCase authorizationByTokenUseCase, RoleRepository roleRepository) {
        super(userRepository, new AdminUserCreateAccess(), authorizationByTokenUseCase);
        this.passwordGenerator = passwordGenerator;
        this.userIdGenerator = userIdGenerator;
        this.roleRepository = roleRepository;
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

        Optional<UserEntity> userByEmail =  userRepository.findByEmail(email.toLowerCase());
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

    public UserEntity create(CreateUserCommand createUserCommand) throws AuthorizationException, TokenExpiredException, AuthenticationException, RoleIsRequiredException, PhoneIsRequiredException, EmailAndPasswordIsRequiredException, UserAlreadyExistException, UserWithPhoneAlreadyExistException {
        checkAccess();

        Set<RoleEntity> roles = createUserCommand.getRoleIds()
                .stream()
                .map(roleId -> roleRepository.findById(roleId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        validate(createUserCommand, roles);

        UserEntity userEntity = new UserEntity();
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

    public void validate(CreateUserCommand createUserCommand, Set<RoleEntity> roles) throws RoleIsRequiredException, PhoneIsRequiredException, EmailAndPasswordIsRequiredException, UserAlreadyExistException, UserWithPhoneAlreadyExistException {

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
            if (createUserCommand.getEmail() == null || createUserCommand.getPassword() == null)
                throw new EmailAndPasswordIsRequiredException();

            if (userRepository.findByEmail(createUserCommand.getEmail()).isPresent())
                throw new UserAlreadyExistException();
        }
    }


}