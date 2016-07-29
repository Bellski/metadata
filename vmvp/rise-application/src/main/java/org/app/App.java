package org.app;

import org.vaadin.rise.core.RisePresenter;
import org.vaadin.rise.core.RiseView;

/**
 * Created by oem on 7/29/16.
 */
public interface App {
	interface View extends RiseView<Presenter> {}
	interface Presenter extends RisePresenter<View> {}
}
