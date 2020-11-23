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
import com.rcore.domain.user.validators.ChangeUserUseCaseValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
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

    public UserEntity create(CreateUserCommand createUserCommand) throws AuthorizationException, TokenExpiredException, AuthenticationException, RoleIsRequiredException, PhoneIsRequiredException, InvalidEmailException, UserAlreadyExistException, UserWithPhoneAlreadyExistException, InvalidFirstNameException, InvalidLastNameException, InvalidRoleException, UserWithEmailAlreadyExistException, InvalidAccountStatusException {
        checkAccess();

        Set<RoleEntity> roles = createUserCommand.getRoles()
                .stream()
                .map(role -> {
                    if (role.getId()!= null)
                        return roleRepository.findById(role.getId());
                    else if (role.getName() != null)
                        return roleRepository.findByName(role.getName());

                    return Optional.<RoleEntity>empty();
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        changeUserUseCaseValidator.validate(createUserCommand);

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


}