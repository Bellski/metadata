package ru.bellski.vmvp.mvp.slots;

import ru.bellski.vmvp.mvp.VMVPPresenterImpl;

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