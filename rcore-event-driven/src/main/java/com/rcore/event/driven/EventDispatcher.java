package com.rcore.event.driven;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class EventDispatcher {

    private EventStorage eventStorage;

    public abstract <E extends Event> void registerHandler(Class<E> eventType, EventHandler<E> handler);

    public abstract <E extends Event> void dispatch(E event);

}
