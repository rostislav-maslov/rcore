package com.rcore.event.driven;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class EventDispatcher {

    private EventStorage eventStorage;

    abstract <E extends Event> void registerHandler(Class<E> eventType, EventHandler<E> handler);

     abstract <E extends Event> void dispatch(E event);

}
