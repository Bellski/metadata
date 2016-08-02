package org.vaadin.rise.slot;

import org.vaadin.rise.core.PopupView;
import org.vaadin.rise.core.RisePresenterComponent;
import org.vaadin.rise.slot.MultiSlot;

/**
 * Created by oem on 8/2/16.
 */
public class PopupSlot<PRESENTER extends RisePresenterComponent<? extends PopupView<?>>> extends MultiSlot<PRESENTER> {

	@Override
	public boolean isPopup() {
		return true;
	}
}
