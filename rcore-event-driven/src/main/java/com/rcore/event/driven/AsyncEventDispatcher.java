package com.rcore.event.driven;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class AsyncEventDispatcher extends DefaultEventDispatcher {

    @Override
    protected <E extends Event> void sendEventToHandlers(Collection<EventHandler<? extends Event>> targetEventHandlers, E event) {
        targetEventHandlers
                .forEach(handler -> {
                    //noinspection unchecked
                    CompletableFuture.runAsync(() -> ((EventHandler<E>) handler).onEvent(event));
                    log.debug("Event {} dispatched to handler {} ", event.getType(), handler.toString());
                });
    }
}
