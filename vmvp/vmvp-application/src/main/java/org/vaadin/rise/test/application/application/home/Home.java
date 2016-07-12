package org.vaadin.rise.test.application.application.home;

import org.vaadin.rise.test.application.mvp.VMVPPresenter;
import org.vaadin.rise.test.application.mvp.VMVPView;

/**
 * Created by oem on 7/11/16.
 */
public interface Home {
	interface View extends VMVPView<Presenter> {

	}

	interface Presenter extends VMVPPresenter<View> {

	}

}
