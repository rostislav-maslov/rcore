package com.rcore.domain.userEmailVerification.port;

import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;

public interface UserEmailVerificationSender {
    public Boolean sendEmail(UserEmailVerificationEntity userEmailVerificationEntity);
}
