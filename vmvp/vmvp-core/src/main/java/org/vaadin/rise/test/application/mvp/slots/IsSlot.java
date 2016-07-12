package org.vaadin.rise.test.application.mvp.slots;

import org.vaadin.rise.test.application.mvp.VMVPPresenterComponent;

public interface IsSlot<PRESENTER extends VMVPPresenterComponent<?>> {
	boolean isPopup();

    boolean isRemovable();

    Object getRawSlot();
}