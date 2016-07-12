package org.vaadin.rise.test.application.mvp.root;

import dagger.Component;
import org.vaadin.rise.test.application.mvp.VMVPPresenter;
import org.vaadin.rise.test.application.mvp.VMVPView;

import javax.inject.Singleton;

/**
 * Created by oem on 7/11/16.
 */
@Singleton
@Component(modules = RootModule.class)
public interface Root {

	interface View extends VMVPView<Presenter> {
		void setUsingRootLayoutPanel(boolean usingRootLayout);
	}

	interface Presenter extends VMVPPresenter<View> {

	}

	RootPresenter presenter();
}
