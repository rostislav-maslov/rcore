package com.rcore.adapter.domain.user;

import com.rcore.domain.user.config.UserConfig;
import lombok.Getter;

@Getter
public class UserAdapter {
    private final UserConfig userConfig;
    public UserAllAdapter all;
    public UserSecureAdapter secure;
    public UserViewAdapter view;

    public UserAdapter(UserConfig userConfig) {
        this.userConfig = userConfig;
        this.all = new UserAllAdapter(userConfig);
        this.secure = new UserSecureAdapter(userConfig);
        this.view = new UserViewAdapter(userConfig);
    }
}
