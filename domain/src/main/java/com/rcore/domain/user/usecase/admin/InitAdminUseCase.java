package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.role.entity.GodModRole;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.IdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;

public class InitAdminUseCase {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final IdGenerator idGenerator;

    public InitAdminUseCase(
            UserRepository userRepository,
            PasswordGenerator passwordGenerator,
            IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.passwordGenerator = passwordGenerator;
        this.idGenerator = idGenerator;
    }

    public Boolean canInit() {
        if (userRepository.count() > 0) return false;
        return true;
    }

    public Boolean init(String email, String password) {
        if (canInit() == false) return false;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(idGenerator.generate());
        userEntity.setEmail(email.toLowerCase());
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), password));
        userEntity.getRoles().add(new GodModRole());
        userRepository.save(userEntity);

        return true;
    }
}
