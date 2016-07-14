package org.vaadin.rise.proxy.slot;

import com.google.web.bindery.event.shared.Event;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.proxy.slot.handler.RevealContentHandler;
import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;

public class NestedSlot extends Event.Type<RevealContentHandler> implements IsSingleSlot<RisePresenterComponent<?>>, RemovableSlot<RisePresenterComponent<?>> {

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
}