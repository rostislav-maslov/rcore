package com.rcore.domain.userEmailVerification.port.impl;

import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationSender;

public class UserEmailVerificationSenderFakeImpl implements UserEmailVerificationSender {
    @Override
    public Boolean sendEmail(UserEmailVerificationEntity userEmailVerificationEntity) {
        return true;
    }
}
