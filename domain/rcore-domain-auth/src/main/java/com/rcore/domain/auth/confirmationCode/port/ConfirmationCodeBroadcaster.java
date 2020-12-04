package com.rcore.domain.auth.confirmationCode.port;

/**
 * Рассылка кодов по получателям
 */
public interface ConfirmationCodeBroadcaster {

    void broadcast(Long limit);

}
