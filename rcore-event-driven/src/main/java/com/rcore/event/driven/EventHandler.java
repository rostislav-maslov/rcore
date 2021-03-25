package com.rcore.event.driven;

/**
 *  Handle event
 * @param <E>
 */
public interface EventHandler<E extends Event> {

    void onEvent(E event);

}
