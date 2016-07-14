package org.vaadin.rise.proxy.slot;

import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.test.application.mvp.VMVPPresenterComponent;

public interface IsSlot<PRESENTER extends RisePresenterComponent<?>> {
	boolean isPopup();

    boolean isRemovable();

    Object getRawSlot();
}