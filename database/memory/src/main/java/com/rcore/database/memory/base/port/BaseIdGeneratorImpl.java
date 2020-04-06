package com.rcore.database.memory.base.port;

import com.rcore.domain.base.port.BaseIdGenerator;

import java.util.UUID;

public class BaseIdGeneratorImpl implements BaseIdGenerator<String> {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String parse(String id) {
        return id;
    }
}
