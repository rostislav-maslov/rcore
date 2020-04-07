package com.rcore.domain.base.port.impl;

import com.rcore.domain.base.exception.InvalidIdException;
import com.rcore.domain.base.port.BaseIdGenerator;

import java.util.UUID;

public class StringIdGenerator implements BaseIdGenerator<String> {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String parse(String id) throws InvalidIdException {
        return id;
    }
}
