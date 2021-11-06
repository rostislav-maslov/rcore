package com.rcore.domain.security.port;

import com.rcore.domain.security.model.CredentialDetails;

/**
 * Сервис идентификации учетных данных
 */
public interface CredentialIdentityService {

    CredentialDetails getCredentialByToken(String token);
}
