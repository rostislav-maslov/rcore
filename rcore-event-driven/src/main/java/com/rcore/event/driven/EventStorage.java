package com.rcore.event.driven;

public interface EventStorage {
    <E extends Event> void put(E event);
}
