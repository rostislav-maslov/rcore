package com.rcore.domain.security.port;

import com.rcore.domain.security.exceptions.AuthenticatedCredentialIsBlockedException;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.exceptions.ParsingTokenException;
import com.rcore.domain.security.exceptions.TokenIsExpiredException;
import com.rcore.domain.security.model.CredentialDetails;

/**
 * Сервис идентификации учетных данных
 */
public interface CredentialIdentityService {

    CredentialDetails getCredentialByToken(String token) throws AuthenticatedCredentialIsBlockedException, CredentialNotFoundException, ParsingTokenException, TokenIsExpiredException;
}
