package com.rcore.event.driven;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class AbstractEvent implements Event {

    protected String id = UUID.randomUUID().toString();
    protected String traceId = UUID.randomUUID().toString();
    protected LocalDateTime date = LocalDateTime.now();

    public AbstractEvent(String traceId) {
        this.traceId = traceId;
    }

    public AbstractEvent(String traceId, LocalDateTime date) {
        this.traceId = traceId;
        this.date = date;
    }

    public AbstractEvent(LocalDateTime date) {
        this.date = date;
    }

    public AbstractEvent() {
    }

    @Override
    public String getType() {
        return getClass().getSimpleName();
    }
}
