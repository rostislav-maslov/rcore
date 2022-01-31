package com.rcore.event.driven;

import java.util.List;

public class EventHandlerRegisterer {

    public void registerHandlers(EventDispatcher eventDispatcher, List<ParametrizedEventHandler> eventHandlers) {
        eventHandlers.forEach(h -> eventDispatcher.registerHandler(h.typeParameterClass, h));
    }

}
