package com.rcore.adapter.domain.role.mapper;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.role.entity.RoleEntity;

public class RoleMapper implements ExampleDataMapper<RoleEntity, RoleDTO> {

    @Override
    public RoleDTO map(RoleEntity roleEntity) {
        return RoleDTO.builder()
                .accesses(roleEntity.getAccesses())
                .createdAt(roleEntity.getCreatedAt())
                .id(roleEntity.getId())
                .locale(roleEntity.getLocale())
                .timeZone(roleEntity.getTimeZone())
                .name(roleEntity.getName())
                .availableAuthTypes(roleEntity.getAvailableAuthTypes())
                .updatedAt(roleEntity.getUpdatedAt())
                .build();
    }

    @Override
    public RoleEntity inverseMap(RoleDTO roleDTO) {
        return RoleEntity.builder()
                .accesses(roleDTO.getAccesses())
                .id(roleDTO.getId())
                .locale(roleDTO.getLocale())
                .name(roleDTO.getName())
                .availableAuthTypes(roleDTO.getAvailableAuthTypes())
                .createdAt(roleDTO.getCreatedAt())
                .updatedAt(roleDTO.getUpdatedAt())
                .timeZone(roleDTO.getTimeZone())
                .build();
    }
}
