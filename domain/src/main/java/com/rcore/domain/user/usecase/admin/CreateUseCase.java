package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.IdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserCreateRole;

public class CreateUseCase extends AdminBaseUseCase {
    private final PasswordGenerator passwordGenerator;
    private final IdGenerator idGenerator;

    public CreateUseCase(UserEntity actor, UserRepository userRepository, PasswordGenerator passwordGenerator, IdGenerator idGenerator) throws AuthorizationException {
        super(actor, userRepository, new AdminUserCreateRole());
        this.passwordGenerator = passwordGenerator;
        this.idGenerator = idGenerator;
    }

    public UserEntity createByEmail(String email, String password) throws UserAlreadyExistException {
        userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UserAlreadyExistException());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(idGenerator.generate());
        userEntity.setEmail(email.toLowerCase());
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), password));

        return userRepository.save(userEntity).get();
    }


}