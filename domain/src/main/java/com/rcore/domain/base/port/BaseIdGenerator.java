package com.rcore.domain.base.port;

import com.rcore.domain.base.exception.InvalidIdException;

public interface BaseIdGenerator<T> {
    String generate();
    T parse(String id) throws InvalidIdException;
}
