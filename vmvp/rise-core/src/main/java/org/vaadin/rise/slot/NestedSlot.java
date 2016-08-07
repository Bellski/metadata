package org.vaadin.rise.slot;

import org.vaadin.rise.core.RisePresenterImpl;
import org.vaadin.rise.slot.api.IsSingleSlot;

/**
 * Created by Aleksandr on 07.08.2016.
 */
public class NestedSlot implements IsSingleSlot<RisePresenterImpl<?>> {
    @Override
    public boolean isPopup() {
        return false;
    }

    @Override
    public boolean isRemovable() {
        return true;
    }
}
