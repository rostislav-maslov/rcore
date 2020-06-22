package com.rcore.domain.user.usecase.all;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class ViewUserUserCase {

    private final UserRepository userRepository;

    public Optional<UserEntity> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> findByPhone(Long phone) {
        return userRepository.findByPhoneNumber(phone);
    }
}
