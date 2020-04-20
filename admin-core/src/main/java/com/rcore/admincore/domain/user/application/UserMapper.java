package com.rcore.admincore.domain.user.application;

import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ExampleDataMapper<UserDTO, com.rcore.admincore.domain.user.web.api.UserDTO> {

    @Override
    public com.rcore.admincore.domain.user.web.api.UserDTO map(UserDTO userDTO) {
        return com.rcore.admincore.domain.user.web.api.UserDTO.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .fails(userDTO.getFails())
                .firstName(userDTO.getFirstName())
                .fullName(userDTO.getFullName())
                .lastFailDate(userDTO.getLastFailDate())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .secondName(userDTO.getSecondName())
                .socialAccounts(userDTO.getSocialAccounts())
                .status(userDTO.getUserStatus())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .build();
    }

    @Override
    public UserDTO inverseMap(com.rcore.admincore.domain.user.web.api.UserDTO userDTO) {
        return UserDTO.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .fails(userDTO.getFails())
                .firstName(userDTO.getFirstName())
                .fullName(userDTO.getFullName())
                .lastFailDate(userDTO.getLastFailDate())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .secondName(userDTO.getSecondName())
                .socialAccounts(userDTO.getSocialAccounts())
                .userStatus(userDTO.getStatus())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .build();
    }
}
