package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.base.roles.BaseRoles;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

@RequiredArgsConstructor
public class InitAdminUseCase {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final UserIdGenerator userIdGenerator;
    private final RoleRepository roleRepository;


    public Boolean canInit() {
        if (userRepository.count() > 0) return false;
        return true;
    }

    public Boolean init(String email, String password) throws AdminUserIsExistException {
        if (canInit() == false) throw new AdminUserIsExistException();

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userIdGenerator.generate());
        userEntity.setEmail(email.toLowerCase());
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), password));
        userEntity.setRoles(Collections.singleton(roleRepository.findByName(BaseRoles.SUPER_USER).get()));
        userEntity = userRepository.save(userEntity);

        return true;
    }
}
