package org.app.popup;

import org.vaadin.rise.core.PopupView;
import org.vaadin.rise.core.RisePresenter;

/**
 * Created by oem on 8/2/16.
 */
public interface PopUp {
	interface View extends PopupView<Presenter> {}
	interface Presenter extends RisePresenter<View> {}
}
