package org.vaadin.rise.core.event;

import com.google.web.bindery.event.shared.Event;

/**
 * Created by Aleksandr on 13.07.2016.
 */
public class RiseEvent extends Event {
    @Override
    public Type getAssociatedType() {
        return null;
    }

    @Override
    protected void dispatch(Object handler) {

    }
}
