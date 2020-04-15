package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.user.config.UserConfig;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAllAdapter {
    private UserMapper userMapper = new UserMapper();
    private final UserConfig userConfig;

    public Optional<UserDTO> findById(String id) {
        return userConfig.all.viewUserUserCase()
                .findById(id)
                .map(userMapper::map);
    }
}
