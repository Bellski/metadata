package org.vaadin.rise.deprecated.proxy.slot;

import org.vaadin.rise.core.PopupView;
import org.vaadin.rise.core.RisePresenterComponent;

/**
 * Created by oem on 8/2/16.
 */
public class PopupSlot<PRESENTER extends RisePresenterComponent<? extends PopupView<?>>> extends MultiSlot<PRESENTER> {

	@Override
	public boolean isPopup() {
		return true;
	}
}
