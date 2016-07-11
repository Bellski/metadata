package ru.bellski.vmvp.mvp.slots;

import ru.bellski.vmvp.mvp.VMVPPresenterComponent;

public interface IsSlot<PRESENTER extends VMVPPresenterComponent<?>> {
	boolean isPopup();

    boolean isRemovable();

    Object getRawSlot();
}