package org.vaadin.rise.slot;

import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.slot.api.IsSingleSlot;
import org.vaadin.rise.slot.api.RemovableSlot;

/**
 * Created by Aleksandr on 07.08.2016.
 */
public class SingleSlot<T extends RisePresenterComponent<?>> implements IsSingleSlot<T>, RemovableSlot<T> {

    @Override
    public boolean isPopup() {
        return false;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }
}
