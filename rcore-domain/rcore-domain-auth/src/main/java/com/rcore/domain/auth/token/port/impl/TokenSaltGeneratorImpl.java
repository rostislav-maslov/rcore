package com.rcore.domain.auth.token.port.impl;

import com.rcore.domain.auth.token.port.TokenSaltGenerator;

import java.util.UUID;

public class TokenSaltGeneratorImpl implements TokenSaltGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
