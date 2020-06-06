package com.rcore.domain.user.usecase.all;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserByPhoneNumber {

    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;

    public UserEntity create(Long phone, RoleEntity role) {
        UserEntity user = new UserEntity(phone);
        user.setId(userIdGenerator.generate());
        user.getRoles().add(role);
        return userRepository.save(user);
    }

}
