package com.rcore.domain.commons.port;

import com.rcore.domain.commons.exception.InvalidIdException;

public interface BaseIdGenerator<T> {
    String generate();
    T parse(String id) throws InvalidIdException;
}
