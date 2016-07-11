package ru.bellski.vmvp.mvp.root;

import ru.bellski.vmvp.mvp.VMVPPresenterComponent;
import ru.bellski.vmvp.mvp.slots.IsSingleSlot;
import ru.bellski.vmvp.mvp.slots.RemovableSlot;

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