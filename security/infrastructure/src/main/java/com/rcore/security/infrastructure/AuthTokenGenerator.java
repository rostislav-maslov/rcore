package com.rcore.security.infrastructure;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.security.infrastructure.exceptions.TokenGenerateException;
import com.rcore.security.infrastructure.exceptions.InvalidTokenFormatException;

public interface AuthTokenGenerator<Model> {
    String generate(Model model, String secret) throws TokenGenerateException;
    Model parseToken(String token, String secret) throws InvalidTokenFormatException, TokenGenerateException, AuthenticationException;
}
