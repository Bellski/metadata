package org.vaadin.rise.core;

/**
 * Created by oem on 8/2/16.
 */
public interface PopupView<PRESENTER extends RisePresenter<?>> extends RiseView<PRESENTER> {
	void hide();
	void show();
}
