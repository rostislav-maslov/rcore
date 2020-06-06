package com.rcore.admincore.domain.user.application;

import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.admincore.domain.user.web.api.UserWeb;
import com.rcore.commons.mapper.ExampleDataMapper;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper implements ExampleDataMapper<UserDTO, UserWeb> {

    public UserWeb map(UserDTO userDTO) {
        return UserWeb.builder()
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

    public UserDTO inverseMap(UserWeb userWeb) {
        return UserDTO.builder()
                .id(userWeb.getId())
                .email(userWeb.getEmail())
                .fails(userWeb.getFails())
                .firstName(userWeb.getFirstName())
                .fullName(userWeb.getFullName())
                .lastFailDate(userWeb.getLastFailDate())
                .lastName(userWeb.getLastName())
                .phoneNumber(userWeb.getPhoneNumber())
                .secondName(userWeb.getSecondName())
                .socialAccounts(userWeb.getSocialAccounts())
                .userStatus(userWeb.getStatus())
                .createdAt(userWeb.getCreatedAt())
                .updatedAt(userWeb.getUpdatedAt())
                .build();
    }
}
