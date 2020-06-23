package com.rcore.adapter.domain.userPasswordRecover;

import com.rcore.adapter.domain.user.UserAllAdapter;
import com.rcore.adapter.domain.user.UserSecureAdapter;
import com.rcore.adapter.domain.user.UserViewAdapter;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.userPasswordRecover.config.UserPasswordRecoverConfig;
import lombok.Getter;

@Getter
public class UserPasswordRecoverAdapter {
    private final UserPasswordRecoverConfig userPasswordRecoverConfig;
    public UserPasswordRecoverAllRecover all;

    public UserPasswordRecoverAdapter(UserPasswordRecoverConfig userPasswordRecoverConfig) {
        this.userPasswordRecoverConfig = userPasswordRecoverConfig;
        this.all = new UserPasswordRecoverAllRecover(this.userPasswordRecoverConfig);
    }
}
