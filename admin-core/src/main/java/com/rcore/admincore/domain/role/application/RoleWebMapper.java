package com.rcore.admincore.domain.role.application;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.admincore.domain.role.web.api.RoleWeb;
import com.rcore.commons.mapper.ExampleDataMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleWebMapper implements ExampleDataMapper<RoleDTO, RoleWeb> {

    @Override
    public RoleWeb map(RoleDTO roleDTO) {
        return RoleWeb.builder()
                .id(roleDTO.getId())
                .accesses(roleDTO.getAccesses())
                .locale(roleDTO.getLocale())
                .name(roleDTO.getName())
                .build();
    }

    @Override
    public RoleDTO inverseMap(RoleWeb roleWeb) {
        return RoleDTO.builder()
                .id(roleWeb.getId())
                .accesses(roleWeb.getAccesses())
                .locale(roleWeb.getLocale())
                .name(roleWeb.getName())
                .build();
    }
}
