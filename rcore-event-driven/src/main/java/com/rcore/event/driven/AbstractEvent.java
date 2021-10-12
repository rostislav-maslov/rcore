package com.rcore.event.driven;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class AbstractEvent implements Event {

    protected String id = UUID.randomUUID().toString();
    protected String traceId = UUID.randomUUID().toString();
    protected Instant date = Instant.now();

    public AbstractEvent(String traceId) {
        this.traceId = traceId;
    }

    public AbstractEvent(String traceId, Instant date) {
        this.traceId = traceId;
        this.date = date;
    }

    public AbstractEvent(Instant date) {
        this.date = date;
    }

    public AbstractEvent() {
    }

    @Override
    public String getType() {
        return getClass().getSimpleName();
    }
}
