package com.rcore.domain.token.port.impl;

import com.rcore.domain.token.port.TokenSaltGenerator;

import java.util.UUID;

public class TokenSaltGeneratorImpl implements TokenSaltGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
