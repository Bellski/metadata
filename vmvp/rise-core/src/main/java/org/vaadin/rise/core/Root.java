package org.vaadin.rise.core;

/**
 * Created by oem on 7/14/16.
 */
public interface Root {
	interface View extends RiseView<Presenter> {

	}

	interface Presenter extends RisePresenter<View> {

	}
}
