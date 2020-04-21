package com.rcore.adapter.domain.role;

import com.rcore.domain.role.config.RoleConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class RoleAdapter {

    private final RoleConfig roleConfig;
    private final RoleAdminAdapter admin;

    public RoleAdapter(RoleConfig roleConfig) {
        this.roleConfig = roleConfig;
        this.admin = new RoleAdminAdapter(roleConfig);
    }
}
