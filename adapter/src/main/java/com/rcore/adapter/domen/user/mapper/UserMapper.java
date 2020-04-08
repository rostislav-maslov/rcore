package com.rcore.adapter.domen.user.mapper;


import com.rcore.adapter.domen.user.model.UserDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.user.entity.UserEntity;

public class UserMapper implements ExampleDataMapper<UserEntity, UserDTO> {
    @Override
    public UserDTO map(UserEntity userEntity) {
        return UserDTO.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .fails(userEntity.getFails())
                .firstName(userEntity.getFirstName())
                .fullName(userEntity.getFullName())
                .lastFailDate(userEntity.getLastFailDate())
                .lastName(userEntity.getLastName())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .phoneNumber(userEntity.getPhoneNumber())
                .roles(userEntity.getRoles())
                .secondName(userEntity.getSecondName())
                .socialAccounts(userEntity.getSocialAccounts())
                .userStatus(userEntity.getUserStatus())
                .build();
    }

    @Override
    public UserEntity inverseMap(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .socialAccounts(userDTO.getSocialAccounts())
                .userStatus(userDTO.getUserStatus())
                .secondName(userDTO.getSecondName())
                .roles(userDTO.getRoles())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .login(userDTO.getLogin())
                .lastName(userDTO.getLastName())
                .lastFailDate(userDTO.getLastFailDate())
                .fullName(userDTO.getFullName())
                .firstName(userDTO.getFirstName())
                .fails(userDTO.getFails())
                .email(userDTO.getEmail())
                .build();
    }
}
