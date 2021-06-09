package com.rcore.domain.security.exceptions;

public enum AuthorizationErrorReason {
    ACCESS_DENIED,
    CREDENTIAL_IS_BLOCKED,
    CONVERTING_TOKEN_ERROR,
    CREDENTIAL_NOT_FOUND,
    INVALID_TOKEN,
    PARSING_TOKEN_ERROR,
    TOKEN_IS_EXPIRED,
    TOKEN_IS_REQUIRED
}
