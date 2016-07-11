package ru.bellski.vmvp.mvp.root;

import dagger.Component;
import ru.bellski.vmvp.mvp.VMVPPresenter;
import ru.bellski.vmvp.mvp.VMVPView;
import ru.bellski.vmvp.proxy.PlaceManager;

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
