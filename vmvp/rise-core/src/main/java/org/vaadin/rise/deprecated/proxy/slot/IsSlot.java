package org.vaadin.rise.deprecated.proxy.slot;

import org.vaadin.rise.core.RisePresenterComponent;

public interface IsSlot<PRESENTER extends RisePresenterComponent<?>> {
	boolean isPopup();

    boolean isRemovable();
}