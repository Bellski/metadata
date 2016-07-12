package org.vaadin.rise.test.application.mvp.root;

import org.vaadin.rise.test.application.mvp.VMVPPresenterComponent;
import org.vaadin.rise.test.application.mvp.slots.RemovableSlot;
import org.vaadin.rise.test.application.mvp.slots.IsSingleSlot;

public class SingleSlot<T extends VMVPPresenterComponent<?>> implements IsSingleSlot<T>, RemovableSlot<T> {
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