package com.rcore.adapter.domain.role;

import com.rcore.domain.role.config.RoleConfig;

public class RoleAdapter {

    public final RoleSecureAdapter secure;
    public final RoleSecureViewAdapter view;

    public RoleAdapter(RoleConfig roleConfig) {
        this.secure = new RoleSecureAdapter(roleConfig);
        this.view = new RoleSecureViewAdapter(roleConfig);
    }
}
