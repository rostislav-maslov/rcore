package com.rcore.event.driven;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;

@Getter
@SuperBuilder
public abstract class AbstractEvent implements Event {
    @Builder.Default
    protected String id = UUID.randomUUID().toString();
    @Builder.Default
    protected String traceId = UUID.randomUUID().toString();
    @Builder.Default
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
