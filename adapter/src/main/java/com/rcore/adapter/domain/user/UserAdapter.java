package com.rcore.adapter.domain.user;

import com.rcore.domain.user.config.UserConfig;
import lombok.Getter;

@Getter
public class UserAdapter {
    private final UserConfig userConfig;
    private UserAllAdapter all;
    private UserAdminAdapter admin;

    public UserAdapter(UserConfig userConfig) {
        this.userConfig = userConfig;
        this.all = new UserAllAdapter(userConfig);
        this.admin = new UserAdminAdapter(userConfig);
    }
}
