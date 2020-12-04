package com.rcore.rest.api.presenter.resources.role.api.response;

import com.rcore.domain.auth.role.entity.RoleEntity;
import lombok.Value;

@Value
public class RoleResponse {
    private final String id;

    public static RoleResponse from(RoleEntity roleEntity) {
        return new RoleResponse(roleEntity.getId());
    }
}
