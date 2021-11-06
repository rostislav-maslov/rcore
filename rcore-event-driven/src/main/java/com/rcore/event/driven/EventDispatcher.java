package com.rcore.event.driven;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class EventDispatcher {

    private EventStorage eventStorage;

    public abstract <E extends Event> void registerHandler(Class<E> eventType, EventHandler<? super E> handler);

    public abstract <E extends Event> void dispatch(E event);

    protected abstract  <E extends Event> void sendEventToHandlers(Collection<EventHandler<? extends Event>> targetEventHandlers, E event);

}
