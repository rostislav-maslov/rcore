package com.rcore.domain.user.usecase.all;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserWithPhoneAlreadyExistForUpdateException;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserByPhoneNumberUseCase {

    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;

    public UserEntity create(Long phone, RoleEntity role) throws UserWithPhoneAlreadyExistForUpdateException {

        if (userRepository.findByPhoneNumber(phone).isPresent())
            throw new UserWithPhoneAlreadyExistForUpdateException();

        UserEntity user = new UserEntity(phone);
        user.setId(userIdGenerator.generate());
        user.getRoles().add(role);
        user = userRepository.save(user);
        return user;
    }

}
