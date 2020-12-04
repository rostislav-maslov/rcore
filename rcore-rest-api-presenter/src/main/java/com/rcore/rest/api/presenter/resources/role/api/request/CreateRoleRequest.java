package com.rcore.rest.api.presenter.resources.role.api.request;

import com.rcore.domain.auth.role.entity.RoleEntity;
import lombok.Value;

import java.util.List;

@Value
public class CreateRoleRequest {
    private final String name;
    private final List<String> accesses;
    private final Boolean hasBoundlessAccess;
    private final List<RoleEntity.AuthType> availableAuthTypes;
}
