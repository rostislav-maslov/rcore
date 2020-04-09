package com.rcore.domain.database.memory.base.port;

import com.rcore.domain.base.port.BaseIdGenerator;
import com.rcore.domain.user.port.IdGenerator;

import java.util.UUID;

public class BaseIdGeneratorImpl implements BaseIdGenerator<String>, IdGenerator<String> {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String parse(String id) {
        return id;
    }
}
