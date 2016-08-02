package org.vaadin.rise.deprecated.proxy.slot;

import org.vaadin.rise.core.PopupView;
import org.vaadin.rise.core.RisePresenterComponent;

/**
 * Created by oem on 8/2/16.
 */
public interface HasPopupsSlot {
	void addToPopupSlot(final RisePresenterComponent<? extends PopupView<?>> child);
	void removeFromPopupSlot(final RisePresenterComponent<? extends PopupView<?>> child);
}
