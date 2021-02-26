package com.rcore.event.driven;

import java.util.UUID;

public abstract class AbstractEvent implements Event {

    protected String id = UUID.randomUUID().toString();
    protected String traceId = UUID.randomUUID().toString();

    public AbstractEvent(String traceId) {
        this.traceId = traceId;
    }

    public AbstractEvent() {
    }

    @Override
    public String getType() {
        return getClass().getSimpleName();
    }
}
