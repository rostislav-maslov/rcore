package com.rcore.security.infrastructure;

import com.rcore.security.infrastructure.jwt.exceptions.JWTGenerateException;
import com.rcore.security.infrastructure.jwt.exceptions.JWTParseException;

public interface AuthTokenGenerator<Model> {
    String generate(Model model, String secret) throws JWTGenerateException;
    Model parseToken(String token, String secret) throws JWTParseException;
}
