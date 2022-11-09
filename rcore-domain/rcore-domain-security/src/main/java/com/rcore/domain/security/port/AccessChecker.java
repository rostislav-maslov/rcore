package com.rcore.domain.security.port;

import com.rcore.domain.security.model.CredentialDetails;

public interface AccessChecker {

    CredentialDetails checkAccessByToken(String token, String requestType, String requestPath, String serviceName);

    boolean tokenIsValid(String token);

}
