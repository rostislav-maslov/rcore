package com.rcore.domain.userEmailVerification.port.impl;

import com.rcore.domain.userEmailVerification.port.UserEmailVerificationLifetime;

public class UserEmailVerificationLifetimeImpl implements UserEmailVerificationLifetime {
    @Override
    public Long emailLifetime() {
        return 1000l * 60 * 60;
    }
}
