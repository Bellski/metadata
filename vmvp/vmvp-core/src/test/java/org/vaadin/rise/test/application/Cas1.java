package org.vaadin.rise.test.application;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;
import org.vaadin.rise.proxy.slot.NestedSlot;

/**
 * Created by oem on 7/12/16.
 */
public interface Cas1 {
	interface View extends RiseView<Presenter> {

	}

	interface Presenter extends RisePresenter<View> {

	}
}
