package com.rcore.domain.base.port;

public interface BaseIdGenerator<T> {
    String generate();
    T parse(String id);
}
