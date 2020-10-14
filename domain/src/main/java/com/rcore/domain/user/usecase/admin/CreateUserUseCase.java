package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserCreateAccess;

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


}