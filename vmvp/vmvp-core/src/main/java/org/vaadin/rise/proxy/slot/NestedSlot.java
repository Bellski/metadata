package org.vaadin.rise.proxy.slot;

import org.vaadin.rise.test.application.mvp.VMVPPresenterImpl;

public class NestedSlot implements IsSingleSlot<VMVPPresenterImpl<?,?>>, RemovableSlot<VMVPPresenterImpl<?,?>> {


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