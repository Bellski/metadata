package org.vaadin.rise.proxy.slot;

import com.google.web.bindery.event.shared.Event;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.proxy.slot.handler.RevealContentHandler;

public class SingleSlot<T extends RisePresenterImpl<?>> extends Event.Type<RevealContentHandler> implements IsSingleSlot<T>, RemovableSlot<T> {
    @Override
    public boolean isPopup() {
        return false;
    }
    @Override
    public boolean isRemovable() {
        return true;
    }
    @Override
    public Object getRawSlot() {
        return this;
    }

    @Override
    public <T1 extends RisePresenterComponent<?>> void setContent(T1 content) {

    }
}