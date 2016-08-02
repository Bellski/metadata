package org.vaadin.rise.slot.api;

import org.vaadin.rise.core.RisePresenterComponent;

public interface IsSlot<PRESENTER extends RisePresenterComponent<?>> {
	boolean isPopup();

    boolean isRemovable();
}