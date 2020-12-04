package com.rcore.domain.auth.confirmationCode.port;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;

public interface SmsConfirmationCodeSender {

    void send(ConfirmationCodeEntity confirmationCodeEntity);
}
