package com.rcore.event.driven;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class AsyncEventDispatcher extends DefaultEventDispatcher {

    @Override
    public <E extends Event> void dispatch(E event) {
        if (getEventStorage() != null)
            getEventStorage().put(event);

        List<EventHandler<? extends Event>> targetEventHandlers = Objects.requireNonNullElse(handlers.get(event.getClass()), new ArrayList<>());

        targetEventHandlers
                .forEach(handler ->
                        CompletableFuture.runAsync(() -> ((EventHandler<E>) handler).onEvent(event)));
    }
}
