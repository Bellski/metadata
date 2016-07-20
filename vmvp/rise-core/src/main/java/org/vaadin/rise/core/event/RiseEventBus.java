package org.vaadin.rise.core.event;

import com.google.web.bindery.event.shared.Event;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.web.bindery.event.shared.ResettableEventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Aleksandr on 13.07.2016.
 */
@Singleton
public class RiseEventBus {
    private final ResettableEventBus eventBus = new ResettableEventBus(new SimpleEventBus());

    @Inject
    public RiseEventBus() {

    }

    public <H extends RiseEventHandler> HandlerRegistration addHandler(Event.Type<H> type, H handler) {
        return eventBus.addHandler(type, handler);
    }

    public <H> HandlerRegistration addHandlerToSource(Event.Type<H> type, Object source, H handler) {
        return eventBus.addHandlerToSource(type, source, handler);
    }

    public void fireEvent(Event<?> event) {
        eventBus.fireEvent(event);
    }

    public void fireEventFromSource(Event<?> event, Object source) {
        eventBus.fireEventFromSource(event, source);
    }


}
